package com.zdatbit.producer;

import com.zdatbit.common.ProducerCommon;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by zhangdi21 on 2020/3/17.
 */
public class Producer {

    public static void main(String[] args) {
        Properties properties = ProducerCommon.properties();

        KafkaProducer<String,String> producer = new KafkaProducer(properties);
        //发送消息
        Future<RecordMetadata> send = producer.send(new ProducerRecord<>("first", 1, "hello", "world"));

        //最后需要释放资源
        producer.close();
    }
}
