package test.work.test.work.component;

import org.springframework.stereotype.Component;
import test.work.test.work.entity.Role;
import test.work.test.work.repository.RoleRepository;

@Component
public class DataLoader {
    private final RoleRepository roleRepository;

    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        LoadRoles();
    }

    private void LoadRoles() {
        roleRepository.save(new Role(1L, "ROLE_USER"));
        roleRepository.save(new Role(2L, "ROLE_ADMIN"));
    }
}
