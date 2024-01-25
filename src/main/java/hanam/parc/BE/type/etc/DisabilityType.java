package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DisabilityType {

    BLIND("BLIND", "시각장애"),
    DEAF("DEAF", "청각장애"),
    MUTE("MUTE", "언어장애"),
    PHYSICAL("PHYSICAL", "지체장애"),
    INTELLECTUAL("INTELLECTUAL", "지적장애"),
    MENTAL("MENTAL", "정신장애"),
    MULTIPLE("MULTIPLE", "복합장애"),
    ETC("ETC", "기타"),
    NONE("NONE", "해당없음");

    private final String key;

    private final String description;
}
