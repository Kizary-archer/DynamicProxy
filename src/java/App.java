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
        primeNumber.getPrimeNumberSet(SetService.getNumSet(1,10));
        primeNumber.getPrimeNumberSet(SetService.getNumSet(1,10));
        primeNumber.getPrimeNumberSet(SetService.getNumSet(1,11));
        System.out.println("cdsd");
    }
}

