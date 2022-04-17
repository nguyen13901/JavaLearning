package allocation;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class StoreParamDto <T>{
    private Map<Long, T> storeParamMap;
    private T sum;

    public StoreParamDto(Map<Long, T> storeParamMap, T indentify, BinaryOperator<T> accumulator) {
        this.storeParamMap = storeParamMap;
        this.sum = storeParamMap.values().stream().reduce(indentify, accumulator);
    }

    public Set<Entry<Long, T>> getStoreParams() {
        return storeParamMap.entrySet();
    }

    public T getStoreParam(Long StoreId) {
        return storeParamMap.get(StoreId);
    }
    public T getSum() {
        return sum;
    }
}
