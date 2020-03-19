package com.zdatbit.common;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by zhangdi21 on 2020/3/19.
 */
public class Common {

    public static Properties properties(){
        Properties properties = new Properties();
        //kafka集群
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG,"");
        //ack配置
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        //key的序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"");
        //value的序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"");
        //等待时间
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        //批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"");
        //缓存大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"");
        //重试次数
        properties.put(CommonClientConfigs.RETRIES_CONFIG,3);

        return properties;
    }
}
