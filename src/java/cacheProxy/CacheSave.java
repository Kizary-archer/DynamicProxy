package cacheProxy;

import cacheProxy.CacheType;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CacheSave {
     CacheType type() default CacheType.JVMMemory;
}
