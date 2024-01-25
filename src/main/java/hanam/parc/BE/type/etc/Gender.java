package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MEN("MEN", "남자"),
    WOMEN("WOMEN", "여자");

    private final String key;

    private final String description;
}
