package allocation;

import model.DataModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static model.DataModel.*;

public class StoreService {

    public static void main(String[] args) {
        // Step 1: Input
        final Integer whAllocationAmount = 300;
        final List<Store> stores = DataModel.getStores();
        final List<Store> slectedStores = stores.stream()
                .filter(store -> Boolean.TRUE.equals(store.getSelected()))
                .collect(Collectors.toList());

        // Validation
        if (slectedStores.size() != 0 ) {
            boolean hasNonNullExpectedSales =
                    slectedStores.stream().anyMatch(store -> store.getExpectedSales() != null);
            if (!hasNonNullExpectedSales) {
                System.out.println("Stop calculation");
                System.out.println("Expected sales cannot be calculated. Please add a " +
                        "reference store or include stores with expected sales for interpolation");
                throw new IllegalArgumentException();
            }
        }

        // Step 2: Do calculation
        Map<Long, Integer> storeAllocatedAmounts = doAllocation(whAllocationAmount, slectedStores);

        // Step 4: Print out result
        storeAllocatedAmounts.entrySet().forEach(System.out::println);
    }

    /**
     * Do Allocation.
     *
     * return map of -> Key: storeId with Long type
     *               -> Value: storeAllocatedAmount after calculation with 4 steps
     *
     * @return map of storeId, storeAllocatedAmount
     */
    private static Map<Long, Integer> doAllocation(Integer whAllocationAmount, List<Store> stores) {
        // TODO: YOUR WORK
        Map<Long, Integer> fixedAllocatedAmountMap;

        // Step 1: Filling in missing expected sales
        Map<Long, BigDecimal> interpolatedExpectedSalesMap = fillMissingExpectedSales(stores);

        // Step 2: Calculate Allocation Key
        StoreParamDto<BigDecimal> interpolatedExpectedSales =
                new StoreParamDto<>(interpolatedExpectedSalesMap, BigDecimal.ZERO, BigDecimal::add);
        Map<Long, BigDecimal> allocationKeyMap = calculateAllocationKey(interpolatedExpectedSales);

        // Step 3: Calculate Allocated Amount
        Map<Long, BigDecimal> stockPreviousDayMap = stores.stream()
                .collect(Collectors.toMap(Store::getStoreId, Store::getStockPreviousDay));
        StoreParamDto<BigDecimal> stockPreviousDay =
                new StoreParamDto<>(stockPreviousDayMap, BigDecimal.ZERO, BigDecimal::add);
        Map<Long, Integer> allocatedAmountMap =
                calculateAllocatedAmount(allocationKeyMap, whAllocationAmount, stockPreviousDay);

        // Step 4: Fix rounding issue
        fixedAllocatedAmountMap = fixRoundingIssue(
                whAllocationAmount, interpolatedExpectedSalesMap, stockPreviousDayMap, allocatedAmountMap);

        return fixedAllocatedAmountMap;
    }

    private static Map<Long, Integer> fixRoundingIssue(
            Integer whAllocationAmount, Map<Long, BigDecimal> interpolatedExpectedSalesMap,
            Map<Long, BigDecimal> stockPreviousDayMap, Map<Long, Integer> allocatedAmountMap) {

        Map<Long, Integer> fixedAllocatedAmountMap = new HashMap<>(allocatedAmountMap);

        StoreParamDto<Integer> allocatedAmount =
                new StoreParamDto<>(allocatedAmountMap, 0, Integer::sum);
        Integer sumAllocatedAmount = allocatedAmount.getSum();

        if (sumAllocatedAmount != whAllocationAmount) {
            Map<Long, Integer> demandMap = calculateDemand(interpolatedExpectedSalesMap,stockPreviousDayMap);

            if (sumAllocatedAmount > whAllocationAmount) {
                while (sumAllocatedAmount.compareTo(whAllocationAmount) > 0) {
                    fixRoundingBiggest(demandMap, fixedAllocatedAmountMap, interpolatedExpectedSalesMap);
                    sumAllocatedAmount -= 1;
                }
            } else {
                while (sumAllocatedAmount.compareTo(whAllocationAmount) < 0) {
                    fixRoundingSmallest(demandMap, fixedAllocatedAmountMap, interpolatedExpectedSalesMap);
                    sumAllocatedAmount += 1;
                }
            }
        }

        return fixedAllocatedAmountMap;
    }

    private static void fixRoundingSmallest(
            Map<Long, Integer> demandMap,
            Map<Long, Integer> allocatedAmountMap, Map<Long, BigDecimal> interpolatedExpectedSalesMap) {

        List<Map.Entry<Long, Integer>> allocatedAmount = allocatedAmountMap.entrySet()
                .stream()
                .collect(Collectors.toList());
        Map.Entry<Long, Integer> selectedStore = allocatedAmount.get(0);

        for (Map.Entry<Long, Integer> allocationDto : allocatedAmount) {
            int smallestDiff = selectedStore.getValue() - demandMap.get(selectedStore.getKey());
            int diff = allocationDto.getValue() - demandMap.get(allocationDto.getKey());

            if (diff < smallestDiff) {
                selectedStore = allocationDto;
                continue;
            }

            if (diff == smallestDiff) {
                int maxDemand = demandMap.get(selectedStore.getKey());
                int demand = demandMap.get(allocationDto.getKey());
                if (demand > maxDemand) {
                    selectedStore = allocationDto;
                    continue;
                }
                if (demand == maxDemand) {
                    BigDecimal maxInterpolatedSales = interpolatedExpectedSalesMap.get(selectedStore.getKey());
                    BigDecimal interpolatedSales = interpolatedExpectedSalesMap.get(selectedStore.getKey());
                    if (interpolatedSales.compareTo(maxInterpolatedSales) > 0) {
                        selectedStore = allocationDto;
                        continue;
                    }
                    if (interpolatedSales.compareTo(maxInterpolatedSales) == 0) {
                        Long minStoreNbr = selectedStore.getKey();
                        Long storeNbr = allocationDto.getKey();
                        if (storeNbr.compareTo(minStoreNbr) < 0) {
                            selectedStore = allocationDto;
                        }
                    }
                }
            }
        }

        int newValue = selectedStore.getValue() - 1;
        selectedStore.setValue(newValue);
    }

    private static void fixRoundingBiggest
            (Map<Long, Integer> demandMap,
             Map<Long, Integer> allocatedAmountMap, Map<Long, BigDecimal> interpolatedExpectedSalesMap) {
        // if a store has “Amount Allocated” = 0, it is skipped and no difference is calculated
        List<Map.Entry<Long, Integer>> nonZeroAllocatedAmountStore = allocatedAmountMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() != 0)
                .collect(Collectors.toList());

        Map.Entry<Long, Integer> selectedStore = nonZeroAllocatedAmountStore.get(0);

        for (Map.Entry<Long, Integer> allocationDto : nonZeroAllocatedAmountStore) {
            int biggestDiff = selectedStore.getValue() - demandMap.get(selectedStore.getKey());
            int diff = allocationDto.getValue() - demandMap.get(allocationDto.getKey());

            if (diff > biggestDiff) {
                selectedStore = allocationDto;
                continue;
            }

            if (diff == biggestDiff) {
                int minDemand = demandMap.get(selectedStore.getKey());
                int demand = demandMap.get(allocationDto.getKey());
                if (demand < minDemand) {
                    selectedStore = allocationDto;
                    continue;
                }
                if (demand == minDemand) {
                    BigDecimal minInterpolatedSales = interpolatedExpectedSalesMap.get(selectedStore.getKey());
                    BigDecimal interpolatedSales = interpolatedExpectedSalesMap.get(selectedStore.getKey());
                    if (interpolatedSales.compareTo(minInterpolatedSales) < 0) {
                        selectedStore = allocationDto;
                        continue;
                    }
                    if (minInterpolatedSales.compareTo(interpolatedSales) == 0) {
                        Long minStoreNbr = selectedStore.getKey();
                        Long stroreNbr = allocationDto.getKey();
                        if (stroreNbr.compareTo(minStoreNbr) < 0) {
                            selectedStore = allocationDto;
                        }
                    }
                }
            }
        }

        int newValue = selectedStore.getValue() - 1;
        selectedStore.setValue(newValue);
    }

    private static Map<Long, Integer> calculateDemand
            (Map<Long, BigDecimal> interpolatedExpectedSalesMap, Map<Long, BigDecimal> stockPreviousDayMap) {
        return interpolatedExpectedSalesMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                e -> {
            BigDecimal interpolatedExpectedSale = e.getValue();
            BigDecimal stockPreviousDay = stockPreviousDayMap.get(e.getKey());
            return Math.max(interpolatedExpectedSale.subtract(stockPreviousDay)
                    .setScale(0, RoundingMode.HALF_UP)
                    .intValueExact(), 0);
        }));
    }

    private static Map<Long, Integer> calculateAllocatedAmount
            (Map<Long, BigDecimal> allocationKeyMap, Integer whAllocationAmount, StoreParamDto<BigDecimal> stockPreviousDay) {

        return allocationKeyMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> {
            BigDecimal allocationKey = e.getValue();
            BigDecimal sumStockPreviousDay = stockPreviousDay.getSum();
            BigDecimal stockPreviousPerDay = stockPreviousDay.getStoreParam(e.getKey());
            return Math.max(allocationKey.multiply(bd(whAllocationAmount)
                    .add(sumStockPreviousDay))
                    .subtract(stockPreviousPerDay)
                    .setScale(0, RoundingMode.HALF_UP)
                    .intValueExact(), 0);
        }));
    }

    private static Map<Long, BigDecimal> fillMissingExpectedSales(List<Store> stores) {
        Map<Long, BigDecimal> interpolatedExpectedSales = new HashMap<>();

        List<Store> ownStore = stores.stream()
                .filter(store -> store.getExpectedSales() != null)
                .collect(Collectors.toList());

        List<BigDecimal> ownExpectedSales = ownStore.stream()
                .map(Store::getExpectedSales)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        BigDecimal avgExpectedSales = calculateAvgExpectedSales(ownExpectedSales);

        Map<Long, BigDecimal> expectedSalesMap = ownStore.stream()
                .collect(Collectors.toMap(Store::getStoreId, Store::getExpectedSales));

        for (Store store : stores) {
            BigDecimal expectedSales = store.getExpectedSales();
            BigDecimal interpolatedExpectedSale = null;
            if (expectedSales != null) {
                interpolatedExpectedSale = expectedSales;
            } else {
                Long referenceStoreId = store.getReferenceStoreId();
                BigDecimal refExpectedSale = expectedSalesMap.get(referenceStoreId);
                if (referenceStoreId != null && refExpectedSale != null) {
                        interpolatedExpectedSale = refExpectedSale;
                } else {
                    interpolatedExpectedSale = avgExpectedSales;
                }
            }
            interpolatedExpectedSales.put(store.getStoreId(), interpolatedExpectedSale);
        }

        return interpolatedExpectedSales;
    }

    private static BigDecimal calculateAvgExpectedSales(List<BigDecimal> ownExpectedSales) {
        BigDecimal sumExpectedSales = ownExpectedSales.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return sumExpectedSales.divide(bd(ownExpectedSales.size()),1, BigDecimal.ROUND_HALF_UP);
    }

    private static Map<Long, BigDecimal> calculateAllocationKey(StoreParamDto<BigDecimal> interpolatedSales) {
        BigDecimal sum = interpolatedSales.getSum();
        return interpolatedSales.getStoreParams().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                e -> e.getValue().divide(sum, 10, RoundingMode.HALF_UP)));
    }
}