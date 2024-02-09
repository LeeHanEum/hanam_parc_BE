package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.Program;
import hanam.parc.BE.type.etc.ApplicationStatus;
import hanam.parc.BE.type.etc.DisabilityType;
import hanam.parc.BE.type.etc.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationResponseDto {

    private Long id;

    private Member member; // 신청자

    private Program program;

    private String address; // 주소

    private String phone; // 연락처

    private Gender gender;

    private DisabilityType disabilityType; // 장애 유형

    private String guardianName; // 보호자 이름

    private String guardianPhone; // 보호자 연락처

    private ApplicationStatus status; // 신청 상태

    private String remarks; // 기타 사항

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}