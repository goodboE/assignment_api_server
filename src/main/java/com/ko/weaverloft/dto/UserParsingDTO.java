package com.ko.weaverloft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserParsingDTO {
    private String name;
    private int age;
    private String city;
}
