package bdd.automation.api.tests;

import bdd.automation.api.support.api.PetApi;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.Matchers.is;

@DisplayName("Tests for animal feature")
public class PetTests extends BaseTest {

    PetApi petApi = new PetApi();

    @Nested
    @DisplayName("List pet test")
    class ListPetTests {

        @ParameterizedTest
        @CsvSource({
                "available, 7",
                "pending,   2",
                "sold,      0"
        })
        @DisplayName("List all pets by state, but sold isn't present")
        void listAllPetsByStatusWhenThereIsNoSold(String status, int quantity) {
            petApi.deletePetByStatus("sold");

            Response actualPetsResponse = petApi.getPetResponseByStatus(status);

            actualPetsResponse.
                    then().
                    statusCode(HttpStatus.SC_OK).
                    body("size()", is(quantity),
                            "findAll { it.status == '" + status + "' }.size()", is(quantity)
                    );
        }
    }
}
