package vmware.services.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vmware.services.user.model.Employee;

import java.util.List;

@FeignClient(name = "employee")
public interface EmployeeClient {

	@GetMapping("/organization/{organizationId}")
	List<Employee> findByOrganization(@PathVariable("organizationId") String organizationId);
	
}
