package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EventResponseDto {

    private Long id;

    private String title;

    private LocalDate start;

    private LocalDate end;

    private String color;

    private String description;

    private Board board;

    private Member member;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
