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
    @CacheSave(type = CacheType.File)
    @Override
    public HashSet<Integer> getPrimeNumberSet(HashSet<Integer> numberSet) throws Exception {
        return (HashSet<Integer>) numberSet.parallelStream()
                .filter(this::isPrime)
                .collect(Collectors.toSet());


    }

}
