package com.bubnii.springBoot_opticsWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = {"age", "email", "firstName", "lastName", "photo", "description"})
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {
    private int id;
    private int age;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String photo;
    private String description;
}
