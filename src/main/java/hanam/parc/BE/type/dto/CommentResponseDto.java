package hanam.parc.BE.type.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;

    private String content;

    private String writer;

    private String createdAt;

    private String updatedAt;
}
