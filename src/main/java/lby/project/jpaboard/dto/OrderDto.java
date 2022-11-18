package lby.project.jpaboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long orderNum;
    private String ordersUser;
    private Date orderDate;

    @Builder
    public OrderDto(Long orderNum, String ordersUser, Date orderDate) {
        this.orderNum = orderNum;
        this.ordersUser = ordersUser;
        this.orderDate = orderDate;
    }
}
