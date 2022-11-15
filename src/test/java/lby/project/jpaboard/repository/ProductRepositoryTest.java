package lby.project.jpaboard.repository;

import lby.project.jpaboard.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findAll() {
        final Product product = new Product("망고", 1);
        productRepository.save(product);

        final List<Product> products = productRepository.findAll();

        Assertions.assertThat(products.size()).isEqualTo(1);
    }

    @Test
    void save() {

    }

}