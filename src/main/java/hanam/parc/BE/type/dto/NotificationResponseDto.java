package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationResponseDto {

    private Long id;

    private String content;

    private Boolean isRead;

    private Member member;

    private String createdAt;
}
