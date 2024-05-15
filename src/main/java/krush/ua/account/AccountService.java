package krush.ua.account;

import krush.ua.account.dtos.SignUpDTO;
import krush.ua.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    @Value("${registration.secret.key}")
    String SECRET_KEY;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return accountRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("No such login exists"));
    }


    public boolean loginIsExist(String login){
        return accountRepository.findByEmail(login).isPresent();
    }

    public boolean registration(SignUpDTO dto){
        if(Objects.equals(dto.getSecretKey(), SECRET_KEY)){
            createNewAccount(dto);
            return true;
        }
        return false;
    }

    private void createNewAccount(SignUpDTO dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Account account = dto.toEntity(dto);
        account.setRoles(roleService.getChooseRole("ADMIN"));
        accountRepository.save(account);
    }
}
