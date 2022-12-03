package lby.project.jpaboard.domain;

import lby.project.jpaboard.exception.NotEnoughStockException;
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
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_cnt")
    private int productCnt;

    @Column(name = "price")
    private int price;

    @Builder
    public Product(String productName, int productCnt, int price) {
        this.productName = productName;
        this.productCnt = productCnt;
        this.price = price;
    }

    public void addStock(int quantity) {
        this.productCnt += quantity;
        System.out.println("this.productCnt = " + this.productCnt);
    }

    public void removeStock(int quantity) {
        int result = productCnt - quantity;
        if (result < 0) {
            throw new NotEnoughStockException("더많은 재고가 필요합니다.");
        }

        this.productCnt = result;
    }

}
