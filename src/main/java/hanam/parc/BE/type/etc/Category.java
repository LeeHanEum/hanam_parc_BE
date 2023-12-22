package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    FREE("FREE", "자유게시판"),
    ETC("ETC", "기타게시판");

    private final String key;

    private final String description;
}
