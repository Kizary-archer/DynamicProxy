package cacheProxy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CacheEntity implements Serializable {

    private Set<Integer> numbers = new HashSet<>();
    private Set<Integer> primeNumbers = new HashSet<>();

    public CacheEntity() {
    }

    public CacheEntity(Set<Integer> numbers, Set<Integer> primeNumbers) {
        this.numbers = numbers;
        this.primeNumbers = primeNumbers;
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }

    public Set<Integer> getPrimeNumbers() {
        return primeNumbers;
    }

}
