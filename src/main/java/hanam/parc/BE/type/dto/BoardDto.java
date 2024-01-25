package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.etc.BoardCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    private String title;

    private String content;

    private BoardCategory boardCategory;

}
