package hanam.parc.BE.type.entity;

import hanam.parc.BE.type.etc.ApplicationStatus;
import hanam.parc.BE.type.etc.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 신청자

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(nullable = false)
    private String address; // 주소

    @Column(nullable = false)
    private String guardianName; // 보호자 이름

    @Column(nullable = false)
    private String guardianPhone; // 보호자 연락처

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status; // 신청 상태

    @Column
    @Size(max = 1000)
    private String remarks; // 기타 사항

    @CreationTimestamp
    @Column(name="created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
