package com.zdatbit.consumer;

import com.zdatbit.common.ConsumerCommon;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @description:自动提交，拉取数据
 */
public class Consumer {

    public static void main(String[] args) {
        Properties properties = ConsumerCommon.properties();

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Arrays.asList("first"));

        while(true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key() + "-----" + consumerRecord.value());
            }
        }
    }
}
