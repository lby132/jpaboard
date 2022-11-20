package lby.project.jpaboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long orderNum;
    private String ordersUser;
    private LocalDateTime orderDate;

    @Builder
    public OrderDto(Long orderNum, String ordersUser, LocalDateTime orderDate) {
        this.orderNum = orderNum;
        this.ordersUser = ordersUser;
        this.orderDate = orderDate;
    }
}
