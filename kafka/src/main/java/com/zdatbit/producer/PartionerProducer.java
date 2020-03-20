package com.zdatbit.producer;

import com.zdatbit.common.ProducerCommon;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @description     自定义分区生产者
 * Created by zhangdi21 on 2020/3/19.
 */
public class PartionerProducer {

    public static void main(String[] args) {
        Properties properties = ProducerCommon.properties();
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.zdatbit.partioner.MyPartioner");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        producer.send(new ProducerRecord<>("topic", 1, "key", "value"), (metadata, exception) -> {
            if(exception!=null){
                System.out.println("处理的结果"+metadata.partition()+"....."+metadata.offset());
            }
        });

        //关闭资源
        producer.close();
    }
}
