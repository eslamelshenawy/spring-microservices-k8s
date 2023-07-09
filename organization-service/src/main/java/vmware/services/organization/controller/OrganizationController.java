package vmware.services.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vmware.services.organization.client.UserClient;
import vmware.services.organization.model.Organization;
import vmware.services.organization.repository.OrganizationRepository;

import java.util.Optional;

@RestController
public class OrganizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	OrganizationRepository repository;
	@Autowired
	UserClient userClient;
	@PostMapping
	public Organization add(@RequestBody Organization organization) {
		LOGGER.info("Organization add: {}", organization);
		return repository.save(organization);
	}
	
	@GetMapping
	public Iterable<Organization> findAll() {
		LOGGER.info("Organization find");
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Organization findById(@PathVariable("id") String id) {
		LOGGER.info("Organization find: id={}", id);
		return repository.findById(id).get();
	}

	@GetMapping("/{id}/with-user")
	public Organization findByIdWithEmployees(@PathVariable("id") String id) {
		LOGGER.info("Organization find: id={}", id);
		Optional<Organization> organization = repository.findById(id);
		if (organization.isPresent()) {
			Organization o = organization.get();
			o.setUsers(userClient.findByOrganization(o.getId()));
			return o;
		} else {
			return null;
		}
	}
}
