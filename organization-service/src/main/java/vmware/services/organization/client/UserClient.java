package vmware.services.organization.client;

import io.fabric8.openshift.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/organization/{organizationId}")
    List<User> findByOrganization(@PathVariable("organizationId") String organizationId);
}
