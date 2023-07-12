package vmware.services.gateway.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vmware.services.gateway.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, Long> {

    User findFirstByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

}
