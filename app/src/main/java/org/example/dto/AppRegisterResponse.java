package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppRegisterResponse {
    private String name;
    private String apikey;
    private String category;

    public AppRegisterResponse() {
    }

    public AppRegisterResponse(String name, String apikey, String category) {
        this.name = name;
        this.apikey = apikey;
        this.category = category;
    }
}
