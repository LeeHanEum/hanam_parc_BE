package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardCategory {

    ANNOUNCEMENT("ANNOUNCEMENT", "공지사항"),
    RECRUITMENT("RECRUITMENT", "채용공고"),
    MANAGEMENT("MANAGEMENT", "경영공시"),
    EVENT("EVENT", "이벤트"),
    NEWS("NEWS", "보도자료");

    private final String key;

    private final String description;
}
