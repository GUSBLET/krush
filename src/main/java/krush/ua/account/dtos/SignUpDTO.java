package krush.ua.account.dtos;

import jakarta.validation.constraints.NotBlank;
import krush.ua.account.Account;
import krush.ua.account.validator.PasswordMatch;
import krush.ua.technical.mapper.Mapper;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatch(password = "password", passwordConfirm = "passwordConfirm")
public class SignUpDTO implements Mapper<SignUpDTO, Account> {

    @NotBlank(message = "Enter your email")
    private String email;

    @NotBlank(message = "Enter password")
    private String password;

    @NotBlank(message = "Confirm password")
    private String passwordConfirm;

    @NotBlank(message = "Enter secretKey")
    private String secretKey;

    @Override
    public SignUpDTO toDto(Account entity) {
        return null;
    }

    @Override
    public Account toEntity(SignUpDTO dto) {
        return Account.builder()
                .email(dto.getEmail())
                .password(dto.password)
                .accountEnabling(true)
                .build();
    }
}
