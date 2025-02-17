package com.aladdin.demobooksite.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;

@Entity
@Data
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
public class Client {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name cannot be blank")
    private String first_name;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name cannot be blank")
    private String last_name;

    @Column(name = "fin")
    @Size(min = 7, max = 7, message = "fin must be exactly 7 characters")
    private String fin;

    @Column(name = "email")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "telephone_number", nullable = false)
    @Pattern(regexp = "\\d{10}", message = "Telephone number must be 10 digits")
    private String telephoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @Column(name = "password")
    private String password;

    public void setFin(@Size(min = 7, max = 7, message = "fin must be exactly 7 characters") String fin) {
        this.fin = fin.toUpperCase();
    }

    public void setPassword(String password) {
        this.password = passwordGenerator(password);
    }

    /*
        @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
        private List<Book> books;

    */
    public static String passwordGenerator(String password) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        char[] charArray = password.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            sb.append(charArray[i]);
            sb.append(secureRandom);
        }
        return sb.substring(0, 18);
    }
}
