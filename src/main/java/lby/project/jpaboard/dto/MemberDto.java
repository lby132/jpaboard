package lby.project.jpaboard.dto;

import lby.project.jpaboard.domain.Address;
import lombok.Data;

@Data
public class MemberDto {

    private String userName;
    private Address address;

    public MemberDto(String userName, Address address) {
        this.userName = userName;
        this.address = address;
    }
}
