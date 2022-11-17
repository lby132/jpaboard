package lby.project.jpaboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_cnt")
    private int productCnt;

    @Builder
    public Product(String productName, int productCnt) {
        this.productName = productName;
        this.productCnt = productCnt;
    }
}
