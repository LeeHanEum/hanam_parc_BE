package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProgramStatus {

    WAITING("WAITING", "대기중"),
    ACCEPTING("ACCEPTING", "접수중"),
    COMPLETED("COMPLETED", "완료"),
    HOLDING("HOLDING", "보류");


    private final String key;

    private final String description;
}
