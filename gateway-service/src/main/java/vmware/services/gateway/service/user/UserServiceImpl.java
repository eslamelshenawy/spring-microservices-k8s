package vmware.services.gateway.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vmware.services.gateway.dto.SignupRequest;
import vmware.services.gateway.dto.UserDto;
import vmware.services.gateway.model.User;
import vmware.services.gateway.repository.UserRepo;
import vmware.services.gateway.service.user.UserService;

import javax.transaction.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public UserDto createUser(SignupRequest signupRequest) throws Exception {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));

        user = userRepo.save(user);
        if (user == null)
            return  null;

        return user.mapUsertoUserDto();
    }


    public Boolean hasUserWithEmail(String email) {
        return userRepo.findFirstByEmail(email) != null;
    }


}
