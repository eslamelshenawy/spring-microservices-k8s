package vmware.services.organization.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vmware.services.organization.model.Organization;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
	
}
