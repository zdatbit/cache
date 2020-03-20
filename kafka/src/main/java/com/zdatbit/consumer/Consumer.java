package com.zdatbit.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put();

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Arrays.asList("first"));
    }
}
