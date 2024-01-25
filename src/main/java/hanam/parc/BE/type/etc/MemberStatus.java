package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberStatus {

    ACTIVE("ACTIVE", "활성 계정"),
    BLOCKED("BLOCKED", "차단 계정"),
    DORMANT("DORMANT", "휴면 계정");

    private final String key;

    private final String description;
}
