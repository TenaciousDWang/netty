package com.nettyDemo.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.nettyDemo.serialize.Serializer;
import com.nettyDemo.serialize.SerializerAlgorithm;

public class JSONSerializer implements Serializer{
    @Override
    public byte getSerializerAlogrithm() {
        
        return SerializerAlgorithm.JSON;
    } 

    @Override
    public byte[] serialize(Object object) {
        
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        
        return JSON.parseObject(bytes, clazz);
    }
}
