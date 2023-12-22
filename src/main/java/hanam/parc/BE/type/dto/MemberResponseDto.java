package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.etc.Role;
import hanam.parc.BE.type.etc.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberResponseDto {

    private String name;

    private String phone;

    private String email;

    private Role role;

    private Status status;

    private LocalDateTime birth;

    private LocalDateTime lastLoginTime;
}
