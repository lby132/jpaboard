package lby.project.jpaboard.dto;

import lby.project.jpaboard.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private Long orderNum;
    private String productName;
    private int productCnt;
    private int price;
    private Order order;

    @Builder
    public ProductDto(Long productId, Long orderNum, String productName, int productCnt, int price, Order order) {
        this.productId = productId;
        this.orderNum = orderNum;
        this.productName = productName;
        this.productCnt = productCnt;
        this.price = price;
        this.order = order;
    }
}
