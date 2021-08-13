package bdd.automation.api.tests;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.api.StoreApi;
import bdd.automation.api.support.domain.Order;
import bdd.automation.api.support.domain.Pet;
import bdd.automation.api.support.domain.builders.OrderBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.is;

@DisplayName("Tests for store feature")
public class StoreTests extends BaseTest {

    @Nested
    @DisplayName("User create new order")
    class CreateNewOrder {
        PetApi petApi = new PetApi();
        StoreApi storeApi = new StoreApi();
        Pet createdPet;

        @BeforeEach
        void setup() {
            Pet pet = Pet.builder()
                    .status("available")
                    .build();

            createdPet = petApi.createPet(pet);
        }

        @Test
        void userMustBeAbleToOrderAvaliablePet() {
            Order order = new OrderBuilder()
                    .withPetId(createdPet.getId())
                    .build();

            Order expectedOrder = storeApi.createOrder(order);

            Response actualOrderResponse = storeApi.getOrder(expectedOrder.getId());
            actualOrderResponse.
                then().
                body("id", is(expectedOrder.getId()),
                        "petId", is(createdPet.getId()),
                        "quantity", is(expectedOrder.getQuantity()),
                        "status", is("approved")
                );
        }

        @AfterEach
        void deletePetsAvailableCreated() {
            petApi.deleteExtraPets("available");
        }
    }
}
