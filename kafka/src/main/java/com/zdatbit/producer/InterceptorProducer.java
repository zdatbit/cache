package com.zdatbit.producer;

import com.zdatbit.common.ProducerCommon;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InterceptorProducer {

    public static void main(String[] args) {
        Properties properties = ProducerCommon.properties();

        List<String> interceptors = new ArrayList<>();
        interceptors.add("com.zdatbit.interceptor.TimeInterceptor");
        interceptors.add("com.zdatbit.interceptor.CountInterceptor");

        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);

        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        producer.send(new ProducerRecord<>("first","key","value"));

        producer.close();
    }
}
