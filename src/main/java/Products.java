import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Products {

    private HashMap<String, Object>[] prod;


    public Products() throws Exception {
        String jsonString = readUrl("https://fakestoreapi.com/products");
        ObjectMapper objectMapper = new ObjectMapper();
        prod = objectMapper.readValue(jsonString, HashMap[].class);
    }

    public Map<String, Double> getCategoryValues() {
        Map<String, Double> categoryValues = new HashMap<>();

        for (HashMap<String, Object> product : prod) {
            String category = (String) product.get("category");
            HashMap<String, Object> obj = (HashMap<String, Object>) product.get("rating");

            int count = (int) obj.get("count");
            Number priceObj = (Number) product.get("price");
            double price = priceObj.doubleValue();
            double totalValue = price * count;
            totalValue = Math.round(totalValue * 100.0) / 100.0;
            categoryValues.put(category, categoryValues.getOrDefault(category, 0.0) + totalValue);
        }

        for (String category : categoryValues.keySet()) {
            double value = categoryValues.get(category);
            double roundedValue = Math.round(value * 100.0) / 100.0;
            categoryValues.put(category, roundedValue);
        }
        return categoryValues;

    }

    private static String readUrl(String urlString) throws IOException {
        try (Scanner scanner = new Scanner(new URL(urlString).openStream(),
                StandardCharsets.UTF_8)) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
