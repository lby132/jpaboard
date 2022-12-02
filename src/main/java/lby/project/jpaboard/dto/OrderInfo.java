package lby.project.jpaboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderInfo {

    private Long productId;
    private Long memberId;
    private int count;
}
