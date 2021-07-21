package bdd.automation.api.support.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    @Builder.Default
    private int id = 10;
    @Builder.Default
    private String username = "theUser";
    @Builder.Default
    private String firstName = "John";
    @Builder.Default
    private String lastName = "James";
    @Builder.Default
    private String email = "john@email.com";
    @Builder.Default
    private String password = "12345";
    @Builder.Default
    private String phone = "12345";
    @Builder.Default
    private int userStatus = 1;
}
