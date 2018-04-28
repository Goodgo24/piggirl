package com.common.util;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/**
 * 处理Gson 转换时间问题
 */
public class GsonBuilderUtil {
	private final static GsonBuilder GsonBuilder = new GsonBuilder();
	private final static Gson Gson = new Gson();
	private static Gson DateGson = null;
	static{
		GsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
		GsonBuilder.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
		DateGson = GsonBuilder.create();
	}
	/**
	 * 创建日期格式化 的gson类
	 * 实例化Gson时使用此类create方法
	 * @return
	 */
	public static Gson createDateGson() {
		return DateGson;
	}
	public static Gson createGson() {
		return Gson;
	}
}
class DateSerializer implements JsonSerializer<Date> {
	public JsonElement serialize(Date src, Type typeOfSrc,JsonSerializationContext context) {
		return new JsonPrimitive(src.getTime());
	}
}
class DateDeserializer implements JsonDeserializer<java.util.Date> {
	public Date deserialize(JsonElement json, Type typeOfT,JsonDeserializationContext context) throws JsonParseException {
		return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
	}
}
