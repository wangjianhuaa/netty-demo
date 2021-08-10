package com.demo.netty.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjianhua
 * @Description 序列化工具类
 * @date 2021/8/6 10:10
 */
public class SerializationUtil {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd();

    private SerializationUtil(){

    }

    /**
     * 把对象序列化为字节数组
     * @param obj 对象
     * @param <T> 泛型
     * @return 字节数组
     */
    public static  <T> byte[] serialize(T obj){
        @SuppressWarnings("unchecked")
        Class<T> cls = (Class<T>) obj.getClass();

        LinkedBuffer  buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try
        {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj,schema,buffer);
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e.getMessage(),e);
        }
        finally
        {
            buffer.clear();
        }
    }

    /**
     * 把字节数组反序列化为对象
     * @param data 数据
     * @param cls 类
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> T deserialize(byte[] data,Class<T> cls){
        try
        {
            T message= objenesis.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            ProtostuffIOUtil.mergeFrom(data,message,schema);
            return message;
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e.getMessage(),e);
        }
    }

    private static <T> Schema<T> getSchema(Class<T>  cls){
        @SuppressWarnings("unchecked")
        Schema<T> schema =(Schema<T>) cachedSchema.get(cls);
        if(schema==null){
            schema = RuntimeSchema.createFrom(cls);
            cachedSchema.put(cls,schema);
        }
        return schema;
    }
}
