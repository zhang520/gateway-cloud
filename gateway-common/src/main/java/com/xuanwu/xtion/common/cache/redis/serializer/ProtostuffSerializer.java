package com.xuanwu.xtion.common.cache.redis.serializer;

import com.xuanwu.xtion.common.exception.AppErrorCode;
import com.xuanwu.xtion.common.exception.AppException;
import com.xuanwu.xtion.common.util.ProtostuffSerializerUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class ProtostuffSerializer<T> implements RedisSerializer {
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return ProtostuffSerializerUtil.serialize(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try {
            return bytes == null || bytes.length <= 0 ? null : ProtostuffSerializerUtil.deserialize(bytes, Object.class);
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new AppException(AppErrorCode.SERIALIZE_ERROR, ex);
        }
    }
}
