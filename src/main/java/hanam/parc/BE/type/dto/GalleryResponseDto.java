package hanam.parc.BE.type.dto;

import hanam.parc.BE.type.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GalleryResponseDto {

    public Long id;

    public String title;

    public Member member;

    public String createdAt;

    public String updatedAt;
}
