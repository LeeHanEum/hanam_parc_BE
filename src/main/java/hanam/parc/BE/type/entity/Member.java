package hanam.parc.BE.type.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import hanam.parc.BE.type.etc.Role;
import hanam.parc.BE.type.etc.Status;
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

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @Column(name="member_id", nullable = false, updatable = false, unique = true)
    private String id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(length = 20)
    private String phone;

    @Column(length = 45)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private LocalDateTime birth;

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
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.status = member.getStatus();
        this.birth = member.getBirth();
        this.createdAt = member.getCreatedAt();
        this.lastLoginTime = member.getLastLoginTime();
    }

}
