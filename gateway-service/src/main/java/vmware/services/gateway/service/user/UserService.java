package vmware.services.gateway.service.user;

import vmware.services.gateway.dto.SignupRequest;
import vmware.services.gateway.dto.UserDto;

public interface UserService {

     UserDto createUser(SignupRequest signupRequest) throws Exception;

     Boolean hasUserWithEmail(String email);


}
