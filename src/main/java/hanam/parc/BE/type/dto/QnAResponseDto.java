package hanam.parc.BE.type.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnAResponseDto {

    private Long id;

    private String title;

    private String content;

    private Boolean isAnswered;

    private String writer;

    private String answer;

    private String answerer;

    private String createdAt;

    private String updatedAt;
}
