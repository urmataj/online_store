package com.example.online_store.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSignUpDto {

    @NotEmpty
    @Size(min = 6, max = 100)
    String username;

    @NotEmpty
    @Size(min = 8)
    String password;

    @NotEmpty
    String fullName;

    @NotEmpty
    String email;
}
