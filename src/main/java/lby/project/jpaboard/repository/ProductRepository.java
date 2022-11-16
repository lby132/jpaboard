package lby.project.jpaboard.repository;

import lby.project.jpaboard.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
