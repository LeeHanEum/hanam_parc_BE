package hanam.parc.BE.type.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopUpResponseDto {

    private Long id;

    private String name;

    private String url;

    private Long size; // 몇 퍼센트로 할지

    private Boolean isShow;

    private String createdAt;

}
