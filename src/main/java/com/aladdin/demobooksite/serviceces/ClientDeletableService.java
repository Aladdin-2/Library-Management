package com.aladdin.demobooksite.serviceces;

import com.aladdin.demobooksite.dao.repository.ClientRepository;
import com.aladdin.demobooksite.exceptions.IllegalArgumentCastException;
import com.aladdin.demobooksite.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientDeletableService {

    private final ClientRepository clientRepository;

    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

    public void deleteClientById(Integer id) {
        clientRepository.deleteById(id);
    }

    public void deleteClientsByFirstNameOrLastName(String firstName,String lastName) {
        int numberOfDeletedUsers = clientRepository.deleteClientByLastNameOrFirstName(firstName,lastName);
        if (numberOfDeletedUsers == 0) {
            throw ResourceNotFoundException.of("There is no client with this first name: {0}", firstName);
        }
    }

    public void deleteClientByAgeRange(int minAge, int maxAge) {
        if (minAge > maxAge) {
            throw IllegalArgumentCastException.of("The minimum age cannot be greater than the maximum age: {0},{1}", minAge, maxAge);
        } else {
            int numberOfDeleteUsers = clientRepository.deleteClientByAgeRange(minAge, maxAge);
            if (numberOfDeleteUsers == 0) {
                throw ResourceNotFoundException.of("There is no client in this parameter: {0},{1}", minAge, maxAge);
            }
        }
    }
    @Transactional
    public void deleteAllClient() {
        clientRepository.deleteAll();
        clientRepository.resetAutoIncrement();
    }
}
