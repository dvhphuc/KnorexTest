package org.java17.exporter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VastExporter {
    public String toJson(Object obj) {
        if (obj == null) {
            return "{}";
        }

        StringBuilder json = new StringBuilder("{");
        Field[] fields = obj.getClass().getDeclaredFields();

        List<String> jsonPairs = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                String jsonValue = formatValue(value);
                jsonPairs.add("\"" + field.getName() + "\":" + jsonValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        json.append(String.join(",", jsonPairs));
        json.append("}");
        return json.toString();
    };


    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "\"" + value + "\"";
        } else if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Collection<?>) { // Handle Lists, Sets
            Collection<?> collection = (Collection<?>) value;
            List<String> jsonList = new ArrayList<>();
            for (Object item : collection) {
                jsonList.add(formatValue(item));
            }
            return "[" + String.join(",", jsonList) + "]";
        } else if (value.getClass().isArray()) { // Handle arrays
            Object[] array = (Object[]) value;
            List<String> jsonArray = new ArrayList<>();
            for (Object item : array) {
                jsonArray.add(formatValue(item));
            }
            return "[" + String.join(",", jsonArray) + "]";
        } else { // Handle nested objects
            return toJson(value);
        }
    }
}
