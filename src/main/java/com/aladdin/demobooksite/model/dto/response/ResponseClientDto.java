package com.aladdin.demobooksite.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClientDto {

    Integer id;
    String first_name;
    String last_name;
    String telephoneNumber;
    int age;

}
