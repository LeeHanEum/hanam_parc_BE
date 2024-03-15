package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    NULL ("NULL", "미지정"),
    MEN("MEN", "남자"),
    WOMEN("WOMEN", "여자");

    private final String key;

    private final String description;
}
