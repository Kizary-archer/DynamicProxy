import Service.SetService;
import cacheProxy.CacheProxy;
import Service.IPrimeNumber;
import Service.PrimeNumberImpl;

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        CacheProxy cacheProxy = new CacheProxy();
        IPrimeNumber primeNumber = cacheProxy.getProxy(new PrimeNumberImpl());

//        System.out.println(primeNumber.isPrime(4));
        HashSet<Integer> hashSet = (HashSet<Integer>) primeNumber.getPrimeNumberSet(1, 3);
        HashSet<Integer> set = (HashSet<Integer>) SetService.getNumSet(1,10);
        System.out.println("cdsd");
    }
}

