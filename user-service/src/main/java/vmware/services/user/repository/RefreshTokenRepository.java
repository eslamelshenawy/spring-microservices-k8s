package vmware.services.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vmware.services.user.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);

}
