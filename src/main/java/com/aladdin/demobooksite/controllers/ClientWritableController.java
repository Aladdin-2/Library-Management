package com.aladdin.demobooksite.controllers;

import com.aladdin.demobooksite.dao.entity.Client;
import com.aladdin.demobooksite.model.dto.response.ResponseClientDto;
import com.aladdin.demobooksite.serviceces.ClientWritableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(path = "aladdin/client/site/")

public class ClientWritableController {

    private final ClientWritableService clientWritableService;

    @PostMapping(path = "/new-client")
    public ResponseClientDto addNewClient(@RequestBody Client client) {
        return clientWritableService.addNewClient(client);
    }


    @PutMapping(path = "/update-client-info/{id}")
    public ResponseClientDto updateClientsPhoneNumber(@PathVariable(name = "id") Integer id,
                                                      @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                                      @RequestParam(name = "email", required = false) String email,
                                                      @RequestParam(name = "address", required = false) String address) {
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        return clientWritableService.updateClientsPhoneNumber(id, phoneNumber, email, address);
    }
}
