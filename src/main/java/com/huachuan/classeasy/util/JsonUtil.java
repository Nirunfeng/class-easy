package com.huachuan.classeasy.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Description: json工具类
 * @Date 2023/2/27 22:04
 **/
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static <T> T parseObject(String json, Class<T> clazz) {
        logger.debug("parseObject start. json={}, class={}", json, clazz.getSimpleName());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));

        try {
            return mapper.readValue(json, clazz);
        } catch (IOException var4) {
            logger.error("parseObject exception.", var4);
            return null;
        }
    }

    public static String toJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException var3) {
            logger.error("toJson exception.", var3);
            return null;
        }
    }

    public static <T> T copyObject(T object, Class<T> clazz) {
        if (object == null) {
            return null;
        } else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setTimeZone(TimeZone.getDefault());
                mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
                long start = System.currentTimeMillis();
                T result = mapper.readValue(mapper.writeValueAsString(object), clazz);
                long cost = System.currentTimeMillis() - start;
                if (cost > 10L) {
                    logger.warn("JsonUtil copyObject cost " + cost + "ms!");
                }

                return result;
            } catch (Exception var8) {
                logger.warn("copyObject error..", var8);
                return null;
            }
        }
    }
}
