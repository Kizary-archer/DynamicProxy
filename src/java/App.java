import Service.IPrimeNumber;
import Service.PrimeNumberImpl;
import Service.SetService;
import cacheProxy.CacheProxy;

public class App {
    public static void main(String[] args) throws Exception {
        CacheProxy cacheProxy = new CacheProxy();
        IPrimeNumber primeNumber = cacheProxy.getProxy(new PrimeNumberImpl());

        primeNumber.getPrimeNumberSet(SetService.getNumSet(1,1000));
        primeNumber.getPrimeNumberSet(SetService.getNumSet(1,1000));
        primeNumber.getPrimeNumberSet(SetService.getNumSet(1,20000)).forEach(System.out::println);
    }
}

