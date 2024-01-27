package hanam.parc.BE.type.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationStatus {

    WAITING("WAITING", "대기중"),
    ACCEPTED("ACCEPTED", "신청완료"),
    CANCELED("CANCELED", "신청취소");

    private final String key;

    private final String description;
}
