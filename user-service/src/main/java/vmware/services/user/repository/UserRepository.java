package vmware.services.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vmware.services.user.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findById(int userId);
    Optional<User> findByName(String username);

}
