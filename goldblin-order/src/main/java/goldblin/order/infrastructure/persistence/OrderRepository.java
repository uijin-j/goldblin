package goldblin.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import goldblin.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
