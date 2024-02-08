package hanam.parc.BE.type.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import hanam.parc.BE.type.etc.DisabilityType;
import hanam.parc.BE.type.etc.Gender;
import hanam.parc.BE.type.etc.MemberRole;
import hanam.parc.BE.type.etc.MemberStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
@Entity(name = "Member")
public class Member {

    @Id
    @Column(name="member_id", nullable = false, updatable = false, unique = true)
    private String id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 15)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(length = 20)
    private String guardianPhone;

    @Column(length = 15)
    private String guardianName;

    @Column(length = 200)
    private String address;

    @Column(length = 45)
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Enumerated(EnumType.STRING)
    private DisabilityType disabilityType;

    @Column
    private LocalDate birth;

    @CreationTimestamp
    @Column(name="created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="last_login_time", nullable = false)
    private LocalDateTime lastLoginTime;

    public Member(Member member) {
        this.id = member.getId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.gender = member.getGender();
        this.phone = member.getPhone();
        this.guardianPhone = member.getGuardianPhone();
        this.guardianName = member.getGuardianName();
        this.address = member.getAddress();
        this.email = member.getEmail();
        this.memberRole = member.getMemberRole();
        this.memberStatus = member.getMemberStatus();
        this.birth = member.getBirth();
        this.disabilityType = member.getDisabilityType();
        this.createdAt = member.getCreatedAt();
        this.lastLoginTime = member.getLastLoginTime();
    }

}
