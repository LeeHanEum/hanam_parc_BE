package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.etc.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {

    private String id;

    private String password;

    private String name;

    private String phone;

    private String email;
}
