package lby.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Members {

    @Id @GeneratedValue
    private Long memberId;
    private String userName;
    private String password;
}
