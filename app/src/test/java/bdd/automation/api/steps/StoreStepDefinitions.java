package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.api.StoreApi;
import bdd.automation.api.support.domain.Order;
import bdd.automation.api.support.domain.Pet;
import bdd.automation.api.support.domain.builders.OrderBuilder;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.is;

public class StoreStepDefinitions {
    PetApi petApi;
    StoreApi storeApi;
    Pet expectedPet;
    Order expectedOrder;

    public StoreStepDefinitions() {
        petApi = new PetApi();
        storeApi = new StoreApi();
    }

    @Dado("que possua animal {word}")
    public void quePossuaAnimalAvaliable(String status) {
        Pet pet = Pet.builder()
                .status(status)
                .build();

        expectedPet = petApi.createPet(pet);
    }

    @Quando("faço o pedido desse animal")
    public void façoOPedidoDesseAnimal() {
        Order order = new OrderBuilder()
            .withPetId(expectedPet.getId())
            .build();

        expectedOrder = storeApi.createOrder(order);
    }

    @Então("o pedido é aprovado")
    public void oPedidoÉAprovado() {
        Response actualOrderResponse = storeApi.getOrder(expectedPet.getId());
        actualOrderResponse.
            then().
                body("id", is(expectedOrder.getId()),
                        "petId", is(expectedPet.getId()),
                        "quantity", is(expectedOrder.getQuantity()),
                        "status", is("approved")
                     );
    }
}
