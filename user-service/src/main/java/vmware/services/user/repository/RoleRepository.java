package vmware.services.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vmware.services.user.model.ERole;
import vmware.services.user.model.Role;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}
