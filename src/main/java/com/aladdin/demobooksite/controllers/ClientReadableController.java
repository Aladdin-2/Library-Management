package com.aladdin.demobooksite.controllers;

import com.aladdin.demobooksite.model.dto.response.ResponseClientDto;
import com.aladdin.demobooksite.serviceces.ClientReadableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "aladdin/client/site/")
@RequiredArgsConstructor
public class ClientReadableController {

    private final ClientReadableService clientReadableService;

    @GetMapping(path = "/get-client/{id}")
    public ResponseClientDto getClient(@PathVariable(name = "id") Integer id) {
        return clientReadableService.getClient(id);
    }

    @GetMapping(path = "/get-all")
    public List<ResponseClientDto> getAllClients() {
        return clientReadableService.getAllClients();
    }

    @GetMapping(path = "/find-client-by")
    public List<ResponseClientDto> findClientByLastNameOrFirstName(@RequestParam(name = "firstName", required = false) String firstName,
                                                                   @RequestParam(name = "lastName", required = false) String lastName) {
        return clientReadableService.findClientByLastNameOrFirstName(firstName, lastName);
    }

    @GetMapping(path = "/sort-client/{choice}")
    public List<ResponseClientDto> sortByFirstNameOrLastName(@PathVariable(name = "choice") String choice) {
        return clientReadableService.sortByFirstNameOrLastName(choice);
    }

}
