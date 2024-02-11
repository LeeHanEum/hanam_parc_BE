package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto {

    private Long id;

    private String title;

    private String content;

    private Member writer;

    private String boardCategory;

    private String createdAt;

    private String updatedAt;

}
