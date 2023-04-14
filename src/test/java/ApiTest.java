import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {

    @Test
    public void productTest() throws Exception {
        // given
        Products prod = new Products();

        // when
        Map<String, Double> categoryValues = prod.getCategoryValues();

        // then
        assertEquals(categoryValues.get("electronics"), 434341.6);
        assertEquals(categoryValues.get("women's clothing"), 55053.61);
        assertEquals(categoryValues.get("men's clothing"), 53840.4);
        assertEquals(categoryValues.get("jewelery"), 294855.0);
    }

    @Test
    public void cartMaxTest() throws Exception {
        // given
        Carts carts = new Carts(0);

        // when
        int cartUserMax = carts.getMax();
        Data maxOwner = new Data(carts.getMaxId()-1);
        String maxOwnerName = maxOwner.getFirstName() + " " + maxOwner.getLastName();

        // then
        assertEquals(cartUserMax, 27);
        assertEquals(maxOwnerName, "john doe");
    }

    @Test
    public void furthestLivingUsersTest() throws Exception {
        // given
        Data data = new Data(0);

        // when
        data.furthestUsers();
        Data[] furthestUsers = data.getFurthest();
        String user1 = furthestUsers[0].getFirstName() + " " + furthestUsers[0].getLastName();
        String user2 = furthestUsers[1].getFirstName() + " " + furthestUsers[1].getLastName();

        // then
        assertEquals(user1, "john doe");
        assertEquals(user2, "derek powell");
    }
}
