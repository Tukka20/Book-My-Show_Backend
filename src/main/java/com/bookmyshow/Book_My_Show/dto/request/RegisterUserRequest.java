package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Email(message = "Invalid Email format")
    @NotBlank(message = "Email Id is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8,message = "Password must contain at least 8 character")
    private String password;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

}
