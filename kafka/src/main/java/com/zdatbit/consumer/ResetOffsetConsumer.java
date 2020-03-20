package com.zdatbit.consumer;

import com.zdatbit.common.ConsumerCommon;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Description：设重置offset
 */
public class ResetOffsetConsumer {

    public static void main(String[] args) {
        Properties properties = ConsumerCommon.properties();
        //重置消费者的offset,有两种情况会生效1：初始化的时候 2：offset失败的时候
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList("first"));

        while(true) {
            ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ZERO);

            for (ConsumerRecord consumerRecord:consumerRecords) {
                System.out.println(consumerRecord.key()+"--------------------"+consumerRecord.value());
            }
        }

    }
}
