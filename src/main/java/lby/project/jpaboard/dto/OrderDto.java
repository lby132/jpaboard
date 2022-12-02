package lby.project.jpaboard.dto;

import lby.project.jpaboard.domain.Member;
import lby.project.jpaboard.domain.OrderStatus;
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
    private LocalDateTime orderDate;
    private Member member;
    private OrderStatus status;

    @Builder
    public OrderDto(LocalDateTime orderDate, Member member, OrderStatus status) {
        this.orderDate = orderDate;
        this.member = member;
        this.status = status;
    }
}
