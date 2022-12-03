package lby.project.jpaboard.form;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class ProductForm {

    private Long productId;
    private String productName;
    private int productCnt;
    private int price;

    @Builder
    public ProductForm(Long productId, String productName, int productCnt, int price) {
        this.productId = productId;
        this.productName = productName;
        this.productCnt = productCnt;
        this.price = price;
    }
}
