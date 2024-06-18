package gr.aueb.NoteApp.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 16, message = "Username must be between 3 and 16 characters")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^.{4,}$", message = "Password must contain at least 4 characters.")
//    @Pattern(regexp = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$/",
//            message = "Password must be 8+ characters with uppercase, lowercase, and a number or special character. Please retry")
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

}
