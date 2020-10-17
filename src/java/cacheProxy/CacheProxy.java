package cacheProxy;

import Service.IPrimeNumber;
import Service.PrimeNumberImpl;
import Service.SetService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.util.Set;

public class CacheProxy {

    CacheEntity cache = new CacheEntity();
    ;


    public CacheProxy() {

    }

    private MethodInterceptor getHandler() {
        return (obj, method, args, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.isAnnotationPresent(CacheSave.class)) {
                if (method.getAnnotation(CacheSave.class).type() == CacheType.JVMMemory) {
                    if (method.getName().equals("getPrimeNumberSet"))
                        return primeNumberCache(args, proxy, obj);
                } else System.out.println("file");
            } else System.out.println("ascasca");
            return proxy.invokeSuper(obj, args);
        };
    }

    public IPrimeNumber getProxy(IPrimeNumber primeNumber) {
        return (IPrimeNumber) Enhancer.create(primeNumber.getClass(), getHandler());
    }

    private Set<Integer> primeNumberCache(Object[] args, MethodProxy proxy, Object obj) throws Throwable {
        Set<Integer> tempSet = SetService.getNumSet((Integer) args[0], (Integer) args[1]);
        IPrimeNumber iPrimeNumber;
        tempSet.removeAll(cache.getNumbers());
        if (tempSet.size() > 0) {
            iPrimeNumber = new PrimeNumberImpl();
            cache.getNumbers().addAll(tempSet);
            for (Integer i : tempSet) {
                if (iPrimeNumber.isPrime(i))
                    cache.getPrimeNumbers().add(i);
            }
        }
        tempSet.retainAll(cache.getPrimeNumbers());
        return tempSet;
    }
}