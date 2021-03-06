package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.domain.Pet;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetStepDefinitions {

    private final PetApi petApi;
    private List<Pet> actualPets;
    private Response actualPetResponseByStatus;

    public PetStepDefinitions() {
        petApi = new PetApi();
    }

    @Dado("que possua animais {word}")
    public void quePossuaAnimaisAvailable(String status) {
    }

    @Quando("pesquisa por todos os animais {word}")
    public void pesquisaPorTodosOsAnimaisAvailable(String status) {
        actualPets = petApi.getPetByStatus(status);
    }

    @Então("retorna a lista de animais {word}")
    public void retornaAListaDeAnimaisAvailable(String status) {
        assertThat(actualPets, is(not(empty())));

        Response actualPetsResponse = petApi.getPetResponseByStatus(status);

        actualPets = actualPetsResponse.body().jsonPath().getList("", Pet.class);

        actualPetsResponse.
            then().
                statusCode(HttpStatus.SC_OK).
                body(
                "size()", is(actualPets.size()),
"findAll { it.status == '"+status+"' }.size()", is(actualPets.size())
                );
    }

    @Então("retorna a lista com {} animal/animais")
    public void retornaAListaComAnimais(int petsQuantity) {
        assertThat(actualPets.size(), is(petsQuantity));
    }

    @Dado("que não possua animais {word}")
    public void queNaoPossuaAnimaisSold(String status) {
        petApi.deletePetByStatus(status);
    }

    @Quando("pesquiso por todos os animais {word}")
    public void pesquisoPorTodosOsAnimaisAvailable(String status) {
        actualPetResponseByStatus = petApi.getPetResponseByStatus(status);
    }

    @Então("recebo a lista com {int} animais {word}")
    public void receboAListaComAnimaisAvailable(int petsQuantity, String status) {
        actualPetResponseByStatus.
            then().
                statusCode(HttpStatus.SC_OK).
                body(
                    "size()", is(petsQuantity),
                    "findAll { it.status == '"+status+"' }.size()", is(petsQuantity)
                );
    }

    @E("{int} animais possuem o nome {word}")
    public void animaisPossuemONomeLion(int petsQuantity, String petName) {
        actualPetResponseByStatus.
            then().
                body(
                  "findAll { it.name.contains('"+petName+"') }.size()", is(petsQuantity)
                );
    }
}
