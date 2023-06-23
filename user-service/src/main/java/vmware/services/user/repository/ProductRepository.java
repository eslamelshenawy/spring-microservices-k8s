package vmware.services.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vmware.services.user.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	@Override
    void delete(Product deleted);
}
