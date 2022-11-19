package lby.project.jpaboard.repository;

import lby.project.jpaboard.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
