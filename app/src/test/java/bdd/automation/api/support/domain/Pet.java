package bdd.automation.api.support.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    @Builder.Default
    private int id = 11;
    @Builder.Default
    private String name = "doggie";
    @Builder.Default
    private Category category = new Category();
    @Builder.Default
    private List<String> photoUrls = Arrays.asList("url1", "url2");
    @Builder.Default
    private String status = "avaliable";

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category {
        @Builder.Default
        private int id = 1;
        @Builder.Default
        private String name = "Dogs";

    }
}
