package hanam.parc.BE.type.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GalleryImageDto {

    private List<String> galleryImageList;

    @Builder
    public GalleryImageDto(List<String> galleryImageList) {
        this.galleryImageList = galleryImageList;
    }
}
