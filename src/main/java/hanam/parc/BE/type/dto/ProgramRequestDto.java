package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.ProgramStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProgramRequestDto {

    private String name; // 프로그램명

    private Long available; // 수용인원

    private String programStatus; // 프로그램 상태

    private String managerId; // 담당자

    private String applyEnd; // 접수 시간(day)

    private String startDate; // 시작일

    private String endDate; // 종료일

    private Long time; // 교육 시간(min)

    private String location; // 장소

    private String cost; // 참가비

    private String material; // 준비물

    private String description; // 설명

}
