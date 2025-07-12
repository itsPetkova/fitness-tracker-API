package org.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class AppRegisterDto {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Category cannot be null")
    private Category category;

    public enum Category {
        BASIC,
        PREMIUM;

        @JsonCreator
        public static Category fromString(String value) {
            return Arrays.stream(values())
                    .filter(c -> c.name().equalsIgnoreCase(value))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category: " + value));
        }
    }
}
