package lby.project.jpaboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private String productName;
    private int productCnt;

    public ProductDto(String productName, int productCnt) {
        this.productName = productName;
        this.productCnt = productCnt;
    }
}
