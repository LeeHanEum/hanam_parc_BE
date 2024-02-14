package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.etc.BoardCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDto {

    private String title;

    private String content;

    private String writerId;

    private BoardCategory boardCategory;

}
