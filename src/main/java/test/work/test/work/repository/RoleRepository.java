package test.work.test.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import test.work.test.work.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
