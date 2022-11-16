package lby.project.jpaboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private Long productId;
    private String productName;
    private int productCnt;
}
