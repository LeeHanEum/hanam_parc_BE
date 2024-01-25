package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.etc.Gender;
import hanam.parc.BE.type.etc.MemberRole;
import hanam.parc.BE.type.etc.MemberStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {

    private String id;

    private String password;

    private String name;

    private Gender gender;

    private String phone;

    private String email;

    private MemberRole memberRole;

    private MemberStatus memberStatus;

    private String birth;

    private String lastLoginTime;
}
