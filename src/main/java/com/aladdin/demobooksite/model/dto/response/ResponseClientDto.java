package com.aladdin.demobooksite.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClientDto {
    private Integer id;
    private String first_name;
    private String last_name;
    private String telephoneNumber;
    private int age;
}
