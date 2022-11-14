package lby.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long productId;
    private String productName;
    private int productCnt;
}
