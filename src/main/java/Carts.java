import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Carts {

    private HashMap<String, Object>[] carts ;
    private List<Map<String, Object>> orders;
    private Map<Integer, Integer> userQuantities;
    private int id;
    private int maxId;


    public Carts(int index) throws Exception {
        String jsonString = readUrl("https://fakestoreapi.com/carts");
        ObjectMapper objectMapper = new ObjectMapper();
        carts = objectMapper.readValue(jsonString, HashMap[].class);
        orders = objectMapper.readValue(jsonString, List.class);
        id = (int) carts[index].get("id");
    }

    public int getMaxId() {
        return maxId;
    }

    public int getMax() {
        userQuantities = new HashMap<>();

        for (Map<String, Object> order : orders) {
            int userId = (int) order.get("userId");
            List<Map<String, Object>> products = (List<Map<String, Object>>) order.get("products");
            int totalQuantity = 0;
            for (Map<String, Object> product : products) {
                int quantity = (int) product.get("quantity");
                totalQuantity += quantity;
            }
            userQuantities.put(userId, userQuantities.getOrDefault(userId, 0) + totalQuantity);
        }

        int maxUserId = 0;
        int maxQuantitySum = 0;
        for (Map.Entry<Integer, Integer> entry : userQuantities.entrySet()) {
            int userId = entry.getKey();
            int quantitySum = entry.getValue();
            if (quantitySum > maxQuantitySum) {
                maxUserId = userId;
                maxQuantitySum = quantitySum;
            }
        }
        maxId = maxUserId;
        return maxQuantitySum;
    }

    private static String readUrl(String urlString) throws IOException {
        try (Scanner scanner = new Scanner(new URL(urlString).openStream(),
                StandardCharsets.UTF_8)) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
