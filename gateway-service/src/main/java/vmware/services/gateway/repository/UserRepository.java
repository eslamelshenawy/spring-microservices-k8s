package vmware.services.gateway.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vmware.services.gateway.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findFirstByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

}
