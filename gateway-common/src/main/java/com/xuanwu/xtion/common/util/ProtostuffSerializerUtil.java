package com.xuanwu.xtion.common.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class ProtostuffSerializerUtil {

    public static <T> byte[] serialize(T t) {
        Schema schema = RuntimeSchema.getSchema(t.getClass());
        return ProtostuffIOUtil.toByteArray(t, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    public static <T> T deserialize(byte[] bs, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        T instance = clazz.newInstance();
        Schema schema = RuntimeSchema.getSchema(clazz);
        ProtostuffIOUtil.mergeFrom(bs, instance, schema);
        return instance;
    }

}
