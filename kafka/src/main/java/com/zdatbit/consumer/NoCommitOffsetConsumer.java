package com.zdatbit.consumer;

import com.zdatbit.common.ConsumerCommon;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Properties;

/**
 * @Description:不自动提交offset
 */
public class NoCommitOffsetConsumer {

    public static void main(String[] args) {
        Properties properties = ConsumerCommon.properties();

        //自动提交设为false,消费者还是会一直消费，offset从本地内存中获取
        //当消费者重新启动时，从头开始消费
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,false);
        properties.remove(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG);


        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        while(true){
            ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ZERO);

            consumerRecords.forEach(consumerRecord->{
                System.out.println(consumerRecord.key()+"----------"+consumerRecord.value());
            });
        }

    }
}
