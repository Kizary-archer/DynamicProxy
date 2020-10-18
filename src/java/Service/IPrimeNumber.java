package Service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public interface IPrimeNumber extends Serializable {

     boolean isPrime(Integer v);
     HashSet<Integer> getPrimeNumberSet(HashSet<Integer> numberSet) throws Exception;

}
