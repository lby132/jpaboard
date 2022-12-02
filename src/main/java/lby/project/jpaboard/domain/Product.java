package lby.project.jpaboard.domain;

import lby.project.jpaboard.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    @NotBlank(message = "상품 이름은 필수 입니다.")
    private String productName;

    @Column(name = "product_cnt")
    private int productCnt;

    @Column(name = "price")
    @NotNull(message = "가격은 필수입니다.")
    private int price;

    @Builder
    public Product(String productName, int productCnt, int price) {
        this.productName = productName;
        this.productCnt = productCnt;
        this.price = price;
    }

    public void addStock(int quantity) {
        this.productCnt += quantity;
    }

    public void removeStock(int quantity) {
        int result = productCnt - quantity;
        if (result < 0) {
            throw new NotEnoughStockException("더많은 재고가 필요합니다.");
        }

        this.productCnt = result;
    }

}
