package com.aladdin.demobooksite.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestClientDto {

    private String first_name;
    private String last_name;
}
