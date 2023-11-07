package kr.co.fns.chat.core.util;


import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GsonUtil {

  public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
  public static final Gson gson = new GsonBuilder()
      .disableHtmlEscaping()
//      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .setDateFormat(DATE_TIME_PATTERN)
      .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
      .setPrettyPrinting()
      .create();

  public static class GsonLocalDateTimeAdapter implements JsonSerializer<LocalDateTime>,
      JsonDeserializer<LocalDateTime> {

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type srcType,
        JsonSerializationContext context) {
      return new JsonPrimitive(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(localDateTime));
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT,
        JsonDeserializationContext context) throws JsonParseException {
      return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
  }
}
