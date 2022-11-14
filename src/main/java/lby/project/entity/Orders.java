package lby.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Orders {

    @Id @GeneratedValue
    private Long orderNum;
    private String ordersUser;
    private Date orderDate;


}
