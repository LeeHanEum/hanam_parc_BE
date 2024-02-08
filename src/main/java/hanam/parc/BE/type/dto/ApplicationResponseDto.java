package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.Program;
import hanam.parc.BE.type.etc.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationResponseDto {

    private Long id;

    private Member member; // 신청자

    private Program program;

    private String address; // 주소

    private String guardianName; // 보호자 이름

    private String guardianPhone; // 보호자 연락처

    private ApplicationStatus status; // 신청 상태

    private String remarks; // 기타 사항

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
