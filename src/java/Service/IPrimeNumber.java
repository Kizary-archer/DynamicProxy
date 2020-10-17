package Service;

import java.io.Serializable;
import java.util.Set;

public interface IPrimeNumber extends Serializable {

    public boolean isPrime(Integer v);
    public Set<Integer> getPrimeNumberSet(int st,int fin) throws Exception;

}