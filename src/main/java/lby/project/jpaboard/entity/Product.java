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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String product_name;
    private int product_cnt;

    @Builder
    public Product(String product_name, int product_cnt) {
        this.product_name = product_name;
        this.product_cnt = product_cnt;
    }
}
