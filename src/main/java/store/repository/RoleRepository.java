package store.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import store.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByRoleName(Role.RoleName roleName);
}
