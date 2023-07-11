package vmware.services.user.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vmware.services.user.model.Role;
import vmware.services.user.model.User;
import vmware.services.user.repository.RoleRepository;
import vmware.services.user.repository.UserRepository;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	RoleRepository roleRepository;

	@PostMapping
	public Role add(@RequestBody Role role) {
		LOGGER.info("Role add: {}", role);
		return roleRepository.save(role);
	}

	@GetMapping
	public Iterable<Role> findAll() {
		LOGGER.info("Role find");
		return roleRepository.findAll();
	}

	@GetMapping("/{id}")
	public Role findById(@PathVariable("id") String id) {
		LOGGER.info("Role find: id={}", id);
		return roleRepository.findById(Integer.valueOf(id)).get();
	}

}
