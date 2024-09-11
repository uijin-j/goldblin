package goldblin.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import goldblin.order.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
