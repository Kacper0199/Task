import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;


public class Data {

    private HashMap<String, Object>[] users;
    private Data[] furthest = new Data[2];
    private static final double EARTH_RADIUS_KM = 6371.0;
    private final double lat;
    private final double lon;
    private String firstName;
    private String lastName;
    private int numUsers = 10;


    public Data(int index) throws Exception {
        String jsonString = readUrl("https://fakestoreapi.com/users");
        ObjectMapper objectMapper = new ObjectMapper();
        users = objectMapper.readValue(jsonString, HashMap[].class);

        HashMap<String, Object> address = (HashMap<String, Object>) users[index].get("address");
        HashMap<String, Object> geo = (HashMap<String, Object>) address.get("geolocation");
        HashMap<String, Object> name = (HashMap<String, Object>) users[index].get("name");

        firstName = (String) name.get("firstname");
        lastName = (String) name.get("lastname");
        lat = Double.parseDouble(geo.get("lat").toString());
        lon = Double.parseDouble(geo.get("long").toString());
    }

    public void furthestUsers() throws Exception {
        Data[] users = new Data[10];
        for (int i = 0; i < numUsers; i++) {
            users[i] = new Data(i);
        }
        calcFurthestUsers(users);
    }

    private void calcFurthestUsers(Data[] users) {
        Data user1 = null;
        Data user2 = null;
        double maxDistance = 0;

        for (int i = 0; i < users.length; i++) {
            for (int j = i + 1; j < users.length; j++) {
                double distance = dist(users[i].getLat(), users[i].getLon(), users[j].getLat(), users[j].getLon());
                if (distance > maxDistance) {
                    maxDistance = distance;
                    user1 = users[i];
                    user2 = users[j];
                }
            }
        }
        furthest[0] = user1;
        furthest[1] = user2;
    }

    private double dist(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1Rad) * Math.cos(lat2Rad);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Data[] getFurthest() {
        return furthest;
    }

    private static String readUrl(String urlString) throws IOException {
        try (Scanner scanner = new Scanner(new URL(urlString).openStream(),
                StandardCharsets.UTF_8)) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
