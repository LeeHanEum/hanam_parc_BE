package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.etc.ApplyMethod;
import hanam.parc.BE.type.etc.DisabilityType;
import hanam.parc.BE.type.etc.ProgramStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProgramDto {

    private String name;

    private String thumbnail;

    private String category;

    private Long available;

    private ProgramStatus programStatus;

    private DisabilityType disabilityType;

    private Long applyPeriod;

    private ApplyMethod applyMethod;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long educationTime;

    private String location;

    private Long cost;

    private String material;

    private String description;
}
