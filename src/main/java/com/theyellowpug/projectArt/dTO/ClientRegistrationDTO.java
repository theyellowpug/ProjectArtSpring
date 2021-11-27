package com.theyellowpug.projectArt.dTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientRegistrationDTO {

    private String email;

    private String password;

    private String passwordAgain;

    private String firstname;

    private String lastname;

    private Date dateOfBirth;
}