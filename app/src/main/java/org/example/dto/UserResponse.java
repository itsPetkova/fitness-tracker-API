package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private long id;
    private String email;
    private List<AppDto> applications;

    public UserResponse() {
    }

    public UserResponse(long id, String email, List<AppDto> applications) {
        this.id = id;
        this.email = email;
        this.applications = applications;
    }

}
