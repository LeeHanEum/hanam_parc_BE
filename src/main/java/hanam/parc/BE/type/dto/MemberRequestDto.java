package hanam.parc.BE.type.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberRequestDto {

    private String id;

    private String password;

    private String name;

    private String phone;

    private String email;

    private LocalDateTime birth;

}
