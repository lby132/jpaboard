package lby.project.jpaboard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
public class Members {

    @Id @GeneratedValue
    private Long memberId;
    private String userName;
    private String password;
}
