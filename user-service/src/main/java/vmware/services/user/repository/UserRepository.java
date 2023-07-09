package vmware.services.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vmware.services.user.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findByEmail(String email);

}
