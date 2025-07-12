package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataEntryDto {
    private Long id;
    private String username;
    private String activity;
    private int duration;
    private int calories;
}