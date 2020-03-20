package com.zdatbit.consumer;

import com.zdatbit.common.ConsumerCommon;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.internals.Topic;

import java.time.Duration;
import java.util.*;

public class CommitOffsetConsumer {

    private static KafkaConsumer<String,String> consumer = null;

    static{
        Properties properties = ConsumerCommon.properties();

        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);

        consumer = new KafkaConsumer(properties);

        consumer.subscribe(Arrays.asList("first"));

    }
    public static void main(String[] args) {



    }


    public static void syncCommit(){
        while(true){
            ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ZERO);
            consumerRecords.forEach(consumerRecord->{
                System.out.println(consumerRecord.key()+"-----------"+consumerRecord.value());
            });
            //同步提交，当前线程会阻塞直到offset提交成功
            consumer.commitSync();
        }
    }


    public static void asynCommit(){
        while(true){
            ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ZERO);
            consumerRecords.forEach(consumerRecord->{
                System.out.println(consumerRecord.key()+"-----------"+consumerRecord.value());
            });

            //不会阻塞，主线程会继续拉取数据
            consumer.commitAsync((offsets, exception) -> {
                if(exception!=null){
                    System.err.println("commit offset exception");
                }
            });
        }
    }



    public static void myCommit(){
        Map<TopicPartition,Long> currentOffset = new HashMap<>();
        consumer.subscribe(Arrays.asList("second"), new ConsumerRebalanceListener() {
            //该方法在rebalance之前调用
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                commitOffset(currentOffset);
            }

            //该方法在rebalance之后调用
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                currentOffset.clear();
                for(TopicPartition partition:partitions){
                    consumer.seek(partition,getOffset(partition));
                }
            }
        });
        while(true){
            ConsumerRecords<String,String> consumerRecords = consumer.poll(Duration.ZERO);
            consumerRecords.forEach(consumerRecord->{
                System.out.println(consumerRecord.key()+"-----------"+consumerRecord.value());
            });


        }
    }

    private static long getOffset(TopicPartition partition){
        return 0;
    }

    private static void commitOffset(Map<TopicPartition,Long> currentOffset) {
    }
}
