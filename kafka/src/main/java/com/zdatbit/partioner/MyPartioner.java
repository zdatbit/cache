package com.zdatbit.partioner;

import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.Cluster;

/**
 * Created by zhangdi21 on 2020/3/19.
 */
public class MyPartioner extends DefaultPartitioner{
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return super.partition(topic, key, keyBytes, value, valueBytes, cluster);
    }
}
