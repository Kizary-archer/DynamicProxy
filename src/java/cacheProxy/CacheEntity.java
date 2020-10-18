package cacheProxy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CacheEntity implements Serializable {

    private HashSet<Integer> numbers = new HashSet<>();
    private HashSet<Integer> primeNumbers = new HashSet<>();

    public CacheEntity() {
    }

    public CacheEntity(HashSet<Integer> numbers, HashSet<Integer> primeNumbers) {
        this.numbers = numbers;
        this.primeNumbers = primeNumbers;
    }

    public HashSet<Integer> getNumbers() {
        return numbers;
    }

    public HashSet<Integer> getPrimeNumbers() {
        return primeNumbers;
    }

}
