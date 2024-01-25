package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplyMethod {

    ONLINE("ONLINE", "온라인"),
    OFFLINE("OFFLINE", "오프라인");

    private final String key;

    private final String description;

}
