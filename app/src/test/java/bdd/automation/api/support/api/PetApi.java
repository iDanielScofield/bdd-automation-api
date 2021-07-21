package bdd.automation.api.support.api;

import bdd.automation.api.support.domain.Pet;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetApi {

    private static final String FIND_PET_BY_STATUS_ENDPOINT = "/v3/pet/findByStatus?status={status}";
    private static final String PET_ENDPOINT = "/v3/pet/{petId}";

    public List<Pet> getPetByStatus(String status) {
        return given().
            pathParam("status", status).
        when().
            get(FIND_PET_BY_STATUS_ENDPOINT).
        then().
            extract().body().jsonPath().getList("", Pet.class);
    }

    public Response getPetResponseByStatus (String status) {
        return given().
            pathParam("status", status).
        when().
            get(FIND_PET_BY_STATUS_ENDPOINT);
    }

    public void deletePetByStatus (String status) {
        List<Integer> petsId = given().
            pathParam("status", status).
        when().
            get(FIND_PET_BY_STATUS_ENDPOINT).
        thenReturn().
            path("id");

        if(!petsId.isEmpty()) {
            for (Integer petId : petsId) {
                given().
                    pathParam("id", petId).
                delete(PET_ENDPOINT);
            }
        }
    }
}
