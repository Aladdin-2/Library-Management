package com.aladdin.demobooksite.dao.repository;

import com.aladdin.demobooksite.dao.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT c FROM Client c WHERE " +
            "(:firstName IS NULL OR LOWER(c.first_name)=LOWER(:firstName)) AND " +
            "(:lastName IS NULL OR LOWER(c.last_name)=LOWER(:lastName) )")
    List<Client> findClientByLastNameOrFirstName(@Param("firstName") String firstName,
                                                 @Param("lastName") String lastName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Client c WHERE " +
            "(:firstName IS NULL OR LOWER(c.first_name) =LOWER(:firstName)) " +
            "AND " +
            "(:lastName IS NULL OR LOWER(c.last_name)=LOWER(:lastName))")
    int deleteClientByLastNameOrFirstName(@Param("firstName") String firstName, @Param("lastName") String lastName);


    @Transactional
    @Modifying
    @Query("DELETE FROM Client c WHERE c.age>=:minAge and c.age<=:maxAge")
    int deleteClientByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    @Modifying
    @Query(value = "ALTER TABLE client AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();


}

