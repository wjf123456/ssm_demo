package com.example.caches.mybatis;

import com.example.utils.SerializeUtil;
import org.apache.ibatis.cache.Cache;
import redis.clients.jedis.Jedis;


import java.util.concurrent.locks.ReadWriteLock;

public class MybatisRedisCache implements Cache {

    private final String id;
    private Jedis jedis = new Jedis("localhost");


    public MybatisRedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("cache------------------------------putObject");
        jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(value));

    }

    @Override
    public Object getObject(Object key) {
        System.out.println("cache------------------------------getObject");
        return jedis.get(SerializeUtil.serialize(key));
    }

    @Override
    public Object removeObject(Object key) {
        System.out.println("cache------------------------------removeObject");
        return jedis.del(SerializeUtil.serialize(key));
    }

    @Override
    public void clear() {
        jedis.flushDB();
    }

    @Override
    public int getSize() {
        return jedis.dbSize().intValue();
    }


}
