package lby.project.jpaboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_num")
    private Long orderNum;

    @OneToMany(mappedBy = "orders")
    private List<Product> products = new ArrayList<>();

    @Column(name = "orders_user")
    private String orderUser;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Builder
    public Orders(Long orderNum, String orderUser, LocalDateTime orderDate) {
        this.orderNum = orderNum;
        this.orderUser = orderUser;
        this.orderDate = orderDate;
    }
}
