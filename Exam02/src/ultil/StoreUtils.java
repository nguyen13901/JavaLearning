package ultil;

import allocation.Store;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class StoreUtils {
    private StoreUtils() {

    }

    public static BigDecimal sum(Collection<Store> stores, Function<Store, BigDecimal> function) {
        return stores.stream()
                .map(function)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
