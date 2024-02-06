package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnAResponseDto {

    private Long id;

    private String title;

    private String content;

    private Boolean isAnswered;

    private Member writer;

    private String answer;

    private Member answerer;

    private String createdAt;

    private String updatedAt;
}
