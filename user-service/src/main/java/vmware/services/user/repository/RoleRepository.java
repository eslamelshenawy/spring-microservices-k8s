package vmware.services.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vmware.services.user.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}
