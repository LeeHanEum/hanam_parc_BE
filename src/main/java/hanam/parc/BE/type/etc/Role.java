package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    SUPER("SUPER_ADMIN", "슈퍼 관리자"),
    ADMIN("ADMIN", "관리자"),
    USER("USER", "사용자");

    private final String key;

    private final String description;
}
