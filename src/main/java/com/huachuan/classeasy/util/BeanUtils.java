package com.huachuan.classeasy.util;

import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.InvocationTargetException;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: 通用工具
 * @Date 2023/2/28 0:50
 **/
public class BeanUtils {
    /**
     * 从一个对象拷贝属性到另一个对象中（只拷贝属性相同的字段）
     *
     * @param dest 目标对象
     * @param orig 原始对象
     */
    public static void copyProperties(Object dest, Object orig) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException | InvocationTargetException ex) {

        }
    }

    /**
     * 对象的深拷贝,从一个实体类型转为另一个类型 且只拷贝属性相同的字段
     * 备注：
     * 1 如果只是单纯的复制，实体类型没有改变，可以使用deepClone方法
     * 2 如果被复制的对象为空，则复制为null
     * 3 支持boolean类型和整型 0,1的转换  0 <-> false  1 <-> true
     * 4 如果是日期类型转string 或string转日期 需要在日期字段上加 @JSONField注解 并指定format参数为格式化类型
     * 5 属性名称不一致的字段无法转换，需要get/set方法单独设置值
     *
     * @param t 被复制的对象
     * @param clazz 转换成的对象类型
     * @param <T> 被复制的对象类型的泛型
     * @param <F> 转换对象类型的泛型
     * @return 复制后的结果
     */
    public static <T, F> F copyProperties(T t, Class<F> clazz) {
        String jsonStr = JsonUtil.toJson(t);
        return JsonUtil.parseObject(jsonStr, clazz);
    }


    /**
     * 给对象赋值，一般适用于动态给对象赋值
     *
     * @param bean 任何对象
     * @param name 属性名
     * @param value 属性的值
     */
    public static void setProperty(Object bean, String name, Object value) {
        try {
            org.apache.commons.beanutils.BeanUtils.setProperty(bean, name, value);
        } catch (IllegalAccessException | InvocationTargetException ex) {

        }
    }

    /**
     * 对象的深拷贝
     * @param t 被复制的对象
     * @param clazz 对象类型
     * @param <T> 泛型
     * @return 复制的对象
     */
    public static <T> T deepClone(T t, Class<T> clazz) {
        String jsonStr = JsonUtil.toJson(t);
        return JsonUtil.parseObject(jsonStr, clazz);
    }
}
