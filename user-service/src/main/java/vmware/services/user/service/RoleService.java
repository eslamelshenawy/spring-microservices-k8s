package vmware.services.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vmware.services.user.model.ERole;
import vmware.services.user.model.Role;
import vmware.services.user.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }

}
