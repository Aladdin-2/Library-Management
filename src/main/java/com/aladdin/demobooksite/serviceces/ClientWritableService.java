package com.aladdin.demobooksite.serviceces;

import com.aladdin.demobooksite.dao.entity.Client;
import com.aladdin.demobooksite.dao.repository.ClientRepository;
import com.aladdin.demobooksite.model.dto.response.ResponseClientDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClientWritableService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    public ResponseClientDto addNewClient(Client client) {
        client.setFin(client.getFin());
        clientRepository.save(client);
/*      String subject = "New information!";
        String linkText = "Confirmation message. Please click the following link to confirm your registration: " + client.getToken() +
                "<a href='http://localhost:8080/aladdin/client/site/confirm?token='> Confirmation message.</a>";
        emailService.sendConfirmationLink(client.getEmail(), subject, linkText);
        */
        return modelMapper.map(client, ResponseClientDto.class);

    }

    /*public void confirmClient(String token) {
        Client client = clientRepository.findToken(token);
        Client topByOrderByIdDesc = clientRepository.findTopByOrderByIdDesc();
        if (client == null) {
            clientRepository.delete(topByOrderByIdDesc);
            throw ResourceNotFoundException.of("Invalid token or token not found! {0}", token);
        }

        if (token.equals(client.getToken())) {
            System.out.println("Token confirmed successfully!");
        }
        }
*/


    @Transactional
    public ResponseClientDto updateClientsPhoneNumber(Integer id, String phoneNumber, String email, String address) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found!"));
        if (Objects.nonNull(phoneNumber)) {
            client.setTelephoneNumber(phoneNumber);
        }
        if (Objects.nonNull(email)) {
            client.setEmail(email);
        }
        if (Objects.nonNull(address)) {
            client.setAddress(address);
        }
        clientRepository.save(client);
        return modelMapper.map(client, ResponseClientDto.class);
    }


}
