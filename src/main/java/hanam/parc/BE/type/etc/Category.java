package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    FREE("FREE", "자유게시판"),
    ANNOUNCEMENT("ANNOUNCEMENT", "공지사항"),
    RECRUITMENT("RECRUITMENT", "채용공고"),
    MANAGEMENT("MANAGEMENT", "경영공시");

    private final String key;

    private final String description;
}
