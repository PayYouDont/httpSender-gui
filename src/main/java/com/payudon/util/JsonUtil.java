package com.payudon.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.payudon.common.config.SensorTypeAdapter;
import com.payudon.common.xml.base.EBD;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {
	
	public static String toJson(Object object) {
		GsonBuilder builder = new GsonBuilder();
		builder.setExclusionStrategies(new GsonIgnoreExclusionStrategy());
		Gson gson = builder.create();
		return gson.toJson(object);
	}
	public static String toJson(Object object, Class<? extends EBD> clazz) {
		Gson gson = new GsonBuilder().registerTypeAdapter(clazz, new SensorTypeAdapter ()).create();
		return gson.toJson(object);
	}

	
	public static <T> T toBean(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
 
    }
	public static <T> List<T> toJsonArray(String jsonStr, Class<T> clazz) {
		 ParameterizedTypeImpl type = new ParameterizedTypeImpl(clazz);
	     Gson gson = new Gson();
	     return gson.fromJson(jsonStr, type);
	}
	public static int i = 0;
	private static class GsonIgnoreExclusionStrategy implements ExclusionStrategy {

		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			return false;
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return false;
		}

	}
	 /**
     * 参数类型转换
     */
    private static class ParameterizedTypeImpl implements ParameterizedType {
        private Class<?> clazz;
 
        public ParameterizedTypeImpl(Class<?> clz) {
            clazz = clz;
        }
 
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }
 
        public Type getRawType() {
            return List.class;
        }
 
        public Type getOwnerType() {
            return null;
        }
    }
}
