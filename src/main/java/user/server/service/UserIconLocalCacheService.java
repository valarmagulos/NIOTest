package user.server.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * description:
 * create: 2018-08-21
 *
 * @author zhun.huang
 */
public class UserIconLocalCacheService {

    private static LoadingCache<String, String> userIconCache = CacheBuilder.newBuilder().maximumSize(1000000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, String>() {
                        @Override
                        public String load(String key) {
                            return UserIconDistributeCacheService.queryByKey(key);
                        }
                    });


    public static String queryByKey(String key) {
        try {
            return userIconCache.get(key);
        } catch (Exception e) {
            System.out.println("用户不存在" + e);
        }
        return "error";
    }


}
