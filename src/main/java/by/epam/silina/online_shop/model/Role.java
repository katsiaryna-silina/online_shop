package by.epam.silina.online_shop.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
//todo serializable
public class Role implements Serializable {
    private Long id;
    private RoleEnum roleEnum;
}
