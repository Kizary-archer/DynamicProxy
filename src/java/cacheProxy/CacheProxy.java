package cacheProxy;

import Service.IPrimeNumber;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CacheProxy {

    CacheEntity cacheJVM = new CacheEntity();
    String cacheFilePath;

    public CacheProxy() {
        cacheFilePath = "cacheFile";
    }

    public CacheProxy(String filePath) throws IOException {
        this.cacheFilePath = filePath;
    }

    public IPrimeNumber getProxy(IPrimeNumber primeNumber) {//создаем прокси объект
        return (IPrimeNumber) Enhancer.create(primeNumber.getClass(), getHandler());
    }

    private MethodInterceptor getHandler() {
        return (obj, method, args, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.isAnnotationPresent(CacheSave.class)) {//обрабатываем только методы с аннотацией
                if (method.getAnnotation(CacheSave.class).type() == CacheType.JVMMemory) {//обработка методов с jvm кешированием
                    if (method.getName().equals("getPrimeNumberSet"))//обработка определённого метода
                        return primeNumberCache(args, proxy, obj, cacheJVM);//кеширование новых данных и возврат результата
                } else//обработка кеширования в файл
                {
                    CacheEntity cacheFile = loadCacheFromFile();//загрузка кеша из файла
                    Set<Integer> res = primeNumberCache(args, proxy, obj, cacheFile);//кеширование новых данных и возврат результата
                    saveCacheToFile(cacheFile);//сохранение новых данных в файл
                    return res;
                }
            }
            return proxy.invokeSuper(obj, args);
        };
    }

    private Set<Integer> primeNumberCache(Object[] args, MethodProxy proxy, Object obj, CacheEntity cache) {//кеширование новых данных
        HashSet<Integer> tempSet = null;
        try {
            HashSet<Integer> outSet = (HashSet<Integer>) args[0];//получаем set искомых значений
            tempSet = new HashSet<>(outSet);
            outSet.removeAll(cache.getNumbers()); //сравниваем со значениями из кеша
            if (outSet.size() > 0) {//если получены необработанные значения
                cache.getPrimeNumbers().addAll((HashSet<Integer>) proxy.invokeSuper(obj, args));//обрабатываем их и записываем в кеш
                cache.getNumbers().addAll(outSet);//записываем их в кеш как обработанные
            }
            tempSet.retainAll(cache.getPrimeNumbers());//получаем из кеша сет простых чисел
        } catch (Throwable t) {
            t.fillInStackTrace();
        }
        return tempSet;
    }

    private CacheEntity loadCacheFromFile() throws IOException {//десериализация
        CacheEntity cacheEntity = new CacheEntity();
        Object x;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFilePath + ".dat"))) {
            x = ois.readObject();
            if (x != null) cacheEntity = (CacheEntity) x;
        } catch (IOException | ClassNotFoundException ioe) {
            System.out.println("Не удалось найти файл!\nСоздан новый файл");
        }
        return cacheEntity;
    }

    private void saveCacheToFile(CacheEntity cacheFile) {//сериализация
        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(cacheFilePath + ".dat"))) {
            ois.writeObject(cacheFile);
        } catch (IOException ioe) {
            System.out.println("Не удалось сохранить файл\nпроверьте доступность каталога");
        }
    }
}