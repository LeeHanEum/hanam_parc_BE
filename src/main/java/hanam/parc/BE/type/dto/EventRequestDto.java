package hanam.parc.BE.type.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventRequestDto {

    private String title;

    private LocalDate start;

    private LocalDate end;

    private String color;

    private String description;

}
