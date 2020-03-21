package com.zdatbit.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class CountInterceptor implements ProducerInterceptor<String,String>{

    private int success;
    private int fail;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

        if(metadata!=null){
            success++;
        }else{
            fail++;
        }
    }

    @Override
    public void close() {
        System.out.println("成功数:"+success+",失败数:"+fail);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
