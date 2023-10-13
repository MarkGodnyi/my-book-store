package store.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import store.lib.RepeatPasswordMatch;

@Data
@RepeatPasswordMatch
public class UserRegistrationRequestDto {
    @Email
    private String email;
    @Size(min = 4, max = 20)
    private String password;
    @Size(min = 4, max = 20)
    private String repeatPassword;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String shippingAddress;
}
