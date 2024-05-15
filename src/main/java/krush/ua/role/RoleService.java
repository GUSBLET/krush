package krush.ua.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Set<Role> getChooseRole(String role) {
        switch (role.toUpperCase()) {
            case "ADMIN" -> {
                return getAdminRights();
            }
            default -> {
                return Set.of(getRole(Authority.ANONYMOUS.name()));
            }
        }
    }

    private Set<Role> getAdminRights() {
        return Set.of(getRole(Authority.ADMIN.name()));
    }

    private Role getRole(String role) {
        return roleRepository.findByRole(role).orElse(null);
    }
}
