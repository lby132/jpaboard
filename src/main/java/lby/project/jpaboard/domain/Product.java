package lby.project.jpaboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "ord_num")
    private Orders orders;

    @Column(name = "product_name")
    @NotBlank(message = "상품 이름은 필수 입니다.")
    private String productName;

    @Column(name = "product_cnt")
    private int productCnt;

    @Column(name = "price")
    @NotNull(message = "가격은 필수입니다.")
    private int price;

    @Builder
    public Product(String productName, int productCnt, int price, Orders orders) {
        this.productName = productName;
        this.productCnt = productCnt;
        this.price = price;
        if (orders != null) {
            this.orders = orders;
            orders.getProducts().add(this);
        }
    }
}
