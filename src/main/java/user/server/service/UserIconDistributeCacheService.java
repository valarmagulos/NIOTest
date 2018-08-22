package user.server.service;

import com.google.common.base.Strings;
import com.sun.deploy.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * description:
 * create: 2018-08-21
 *
 * @author zhun.huang
 */
public class UserIconDistributeCacheService {

    static Jedis jedis = null;
    static Random random =new Random();
    static {
        try {
        //连接redis服务器，127.0.0.1:6379
        jedis = new Jedis("127.0.0.1", 6379);
        //权限认证
        jedis.auth("123456");
        } catch (Exception ex) {
            System.out.println("jedis初始化失败" + ex);
        }
    }

    public static String queryByKey(String key) {
        if (jedis == null) {
            throw new RuntimeException("jedis初始化失败");
        }
        String iconUrl = jedis.get(key);
        if (Strings.isNullOrEmpty(iconUrl)) {
            String mockIconUrl = "http://www.userIcon/" + random.nextLong() + ".jpg";
            iconUrl = mockIconUrl;
            jedis.set(key,mockIconUrl);
        }
        return iconUrl;
    }
}
