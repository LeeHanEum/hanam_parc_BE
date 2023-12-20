package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.Role;
import hanam.parc.BE.type.etc.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {

    private String name;

    private String email;

    private Role role;

    private Status status;

    private LocalDateTime birth;

    private LocalDateTime lastLoginTime;

    @Builder
    public MemberDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.status = member.getStatus();
        this.birth = member.getBirth();
        this.lastLoginTime = member.getLastLoginTime();
    }
}
