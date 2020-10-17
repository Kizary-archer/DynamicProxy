package Service;

import cacheProxy.CacheSave;
import cacheProxy.CacheType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class PrimeNumberImpl implements IPrimeNumber {

    @Override
    public boolean isPrime(Integer v) {
        if (v < 2) return false;
        return IntStream.rangeClosed(2, (int) Math.sqrt(v))
                .noneMatch(value -> v % value == 0);
    }
    @CacheSave(type = CacheType.JVMMemory)
    @Override
    public Set<Integer> getPrimeNumberSet(int st, int fin) throws Exception {
        if (st > fin) throw new Exception("начальное значение больше конечного");
        return IntStream.rangeClosed(st, fin)
                .filter(this::isPrime)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));
    }

}
