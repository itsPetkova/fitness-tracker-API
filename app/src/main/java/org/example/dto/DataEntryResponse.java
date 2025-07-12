package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataEntryResponse {
        private Long id;
        private String username;
        private String activity;
        private int duration;
        private int calories;
        private String application;
}
