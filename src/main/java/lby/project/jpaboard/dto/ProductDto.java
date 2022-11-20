package lby.project.jpaboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private Long orderNum;
    private String productName;
    private int productCnt;
    private int price;

    @Builder
    public ProductDto(String productName, int productCnt, int price) {
        this.productName = productName;
        this.productCnt = productCnt;
        this.price = price;
    }
}
