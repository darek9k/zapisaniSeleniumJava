package webdriver.test;

import com.google.gson.*;

import java.util.Map;

public class JsonParserHelper {

    public static String getCountValue(String pageSource, String productName) {
        int startIndex = pageSource.indexOf("{\"definition\":");
        int endIndex = pageSource.lastIndexOf("}") + 1;

        String jsonResponse = pageSource.substring(startIndex, endIndex);

        // Konwertuje JSON do obiektu Java przy użyciu Gson
        Gson gson = new Gson();
        JsonElement jsonElement = JsonParser.parseString(jsonResponse);

        // Znajdź obiekt o zadanej nazwie i wyciągnij jego "count"
        return findCountValue(jsonElement, productName);
    }

    private static String findCountValue(JsonElement jsonElement, String productName) {
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                JsonElement value = entry.getValue();
                if (entry.getKey().equals("count") && value.isJsonPrimitive() && productName.equals(jsonObject.getAsJsonPrimitive("text").getAsString())) {
                    return value.getAsString();
                } else if (value.isJsonObject() || value.isJsonArray()) {
                    String countValue = findCountValue(value, productName);
                    if (countValue != null) {
                        return countValue;
                    }
                }
            }
        } else if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement arrayElement : jsonArray) {
                String countValue = findCountValue(arrayElement, productName);
                if (countValue != null) {
                    return countValue;
                }
            }
        }
        return null;
    }
}
