//package lby.project.jpaboard.repository;
//
//import lby.project.jpaboard.dto.ProductDto;
//import lby.project.jpaboard.entity.Product;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//@Slf4j
//public class ProductRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public List<Product> findAll() {
//        return em.createQuery("select p from Product p", Product.class)
//                .getResultList();
//    }
//
//    public void save(ProductDto productDto) {
//        final Product product = Product.builder()
//                .product_name(productDto.getProductName())
//                .product_cnt(productDto.getProductCnt())
//                .build();
//        em.persist(product);
//    }
//
//    public Product findOne(Long id) {
//        return em.find(Product.class, id);
//    }
//
//}
