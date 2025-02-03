package com.aladdin.demobooksite.controllers;

import com.aladdin.demobooksite.serviceces.ClientDeletableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "aladdin/site/client")
@RequiredArgsConstructor
public class ClientDeletableController {

    private final ClientDeletableService clientDeletableService;

    @DeleteMapping(path = "/delete/{id}")
    public void deleteClientById(@PathVariable(name = "id") Integer id) {
        clientDeletableService.deleteClientById(id);
    }

    @DeleteMapping(path = "/delete/param")
    public void deleteClientsByFirstName(@RequestParam(name = "firstName",required = false) String firstName,
                                         @RequestParam(name = "lastName",required = false) String lastName) {
        clientDeletableService.deleteClientsByFirstNameOrLastName(firstName, lastName);
    }

    @DeleteMapping(path = "delete/{minAge}/{maxAge}/client")
    public void deleteClientByAgeRange(@PathVariable("minAge") int minAge, @PathVariable("maxAge") int maxAge) {
        clientDeletableService.deleteClientByAgeRange(minAge, maxAge);
    }

    @DeleteMapping(path = "/delete-all-clients")
    public void deleteAllClient() {
        clientDeletableService.deleteAllClients();
    }

}
