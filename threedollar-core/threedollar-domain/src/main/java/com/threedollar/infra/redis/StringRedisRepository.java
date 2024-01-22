package com.threedollar.infra.redis;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface StringRedisRepository<K extends StringRedisKey<K, V>, V> {

    V get(K k);

    Map<K, V> getBulk(List<K> keys);

    void set(K k, V v);

    void setBulk(Map<K, V> keyValues);

    void setWithTtl(K k, V v, Duration ttl);

    void incr(K k);

    void incrBulk(List<K> keys);

    void incrBy(K k, long value);

    void decr(K k);

    void decrBulk(List<K> keys);

    void decrBy(K k, long value);

    void del(K k);

    void delBulk(List<K> keys);
}
