package lby.project.jpaboard.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long productId;
    private String productName;
    private int productCnt;
}
