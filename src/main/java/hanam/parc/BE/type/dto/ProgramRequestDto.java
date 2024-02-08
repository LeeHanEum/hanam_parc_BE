package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.ProgramStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProgramRequestDto {

    private String name; // 프로그램명

    private String thumbnail; // 썸네일

    private String category; // 종목

    private Long available; // 수용인원

    private ProgramStatus programStatus; // 프로그램 상태

    private Member manager; // 담당자

    private LocalDateTime applyEnd; // 접수 시간(day)

    private LocalDate startDate; // 시작일

    private LocalDate endDate; // 종료일

    private Long educationTime; // 교육 시간(min)

    private String location; // 장소

    private String cost; // 참가비

    private String material; // 준비물

    private String description; // 설명

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
