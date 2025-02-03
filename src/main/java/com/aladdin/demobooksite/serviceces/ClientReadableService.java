package com.aladdin.demobooksite.serviceces;

import com.aladdin.demobooksite.dao.entity.Client;
import com.aladdin.demobooksite.dao.repository.ClientRepository;
import com.aladdin.demobooksite.exceptions.ResourceNotFoundException;
import com.aladdin.demobooksite.model.dto.response.ResponseClientDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientReadableService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ResponseClientDto getClient(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found!"));
        return modelMapper.map(client, ResponseClientDto.class);
    }

    public List<ResponseClientDto> getAllClients() {
        List<Client> allClients = clientRepository.findAll();
        if (!allClients.isEmpty()) {
            return allClients.stream().map(client -> modelMapper.map(client, ResponseClientDto.class)).toList();
        } else {
            throw new ResourceNotFoundException("The database is empty!");
        }
    }

    public List<ResponseClientDto> findClientByLastNameOrFirstName(String firstName, String lastName) {
        List<Client> clients = clientRepository.findClientByLastNameOrFirstName(firstName, lastName);
        if (!clients.isEmpty()) {
            return clients.stream().map(client -> modelMapper.map(client, ResponseClientDto.class)).toList();
        }
        throw ResourceNotFoundException.of("There are no users with the name  \"{0}\" or the surname \"{1}\"", firstName, lastName);
    }

    public List<ResponseClientDto> sortByFirstNameOrLastName(String choice) {
        List<Client> allClients = clientRepository.findAll();
        if (!allClients.isEmpty()) {
            if (choice.equalsIgnoreCase("firstName")) {
                return allClients.stream()
                        .sorted(Comparator.comparing(Client::getFirst_name))
                        .map(client -> modelMapper.map(client, ResponseClientDto.class))
                        .toList();
            }
            if (choice.equalsIgnoreCase("lastName")) {
                return allClients.stream()
                        .sorted(Comparator.comparing(Client::getLast_name))
                        .map(client -> modelMapper.map(client, ResponseClientDto.class))
                        .toList();
            }
            if (choice.equalsIgnoreCase("age")) {
                return allClients.stream()
                        .sorted(Comparator.comparingInt(Client::getAge))
                        .map(client -> modelMapper.map(client, ResponseClientDto.class))
                        .toList();
            }
        }
        throw new ResourceNotFoundException("The database is empty!");
    }

}
