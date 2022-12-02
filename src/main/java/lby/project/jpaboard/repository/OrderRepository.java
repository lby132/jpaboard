package lby.project.jpaboard.repository;

import lby.project.jpaboard.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.id = :id")
    Order findOrder(@Param("id") Long id);
}
