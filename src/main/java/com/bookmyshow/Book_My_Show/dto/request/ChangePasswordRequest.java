package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class ChangePasswordRequest {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmPassword;


}
