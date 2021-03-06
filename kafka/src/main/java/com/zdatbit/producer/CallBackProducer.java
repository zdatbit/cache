package com.zdatbit.producer;

import com.zdatbit.common.ProducerCommon;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by zhangdi21 on 2020/3/19.
 */
public class CallBackProducer {

    public static void main(String[] args) {

        Properties properties = ProducerCommon.properties();

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        producer.send(new ProducerRecord<>("topic", "hello"), (metadata, exception) -> {
            if(exception!=null){
                System.out.println("处理结果是："+metadata.partition()+"...."+metadata.offset());
            }else{
                exception.printStackTrace();
            }
        });

        producer.close();
    }
}
