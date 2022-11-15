package lby.project.jpaboard.controller;

import lby.project.jpaboard.entity.Product;
import lby.project.jpaboard.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
class ProductListControllerTest {

    Logger log = (Logger) LoggerFactory.getLogger(ProductListControllerTest.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductListController productListController;

    @Test
    void findAll() {

    }

    @Test
    void save() {

    }
}