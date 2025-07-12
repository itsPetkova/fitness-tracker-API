package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppDto {
    private Long id;
    private String name;
    private String description;
    private String apikey;
    private String category;

    public AppDto() {
    }
}
