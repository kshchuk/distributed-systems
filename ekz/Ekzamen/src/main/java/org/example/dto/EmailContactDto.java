package org.example.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Data
public class EmailContactDto extends ContactDto implements java.io.Serializable {
    private String email;

    public EmailContactDto() {
    }

    public EmailContactDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() + "email: " + email;
    }
}