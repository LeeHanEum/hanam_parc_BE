package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.GalleryRequestDto;
import hanam.parc.BE.type.dto.GalleryResponseDto;
import hanam.parc.BE.type.entity.Gallery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GalleryMapper {

    GalleryMapper INSTANCE = Mappers.getMapper(GalleryMapper.class);

    Gallery GalleryRequestDtoToGallery(GalleryRequestDto galleryRequestDto);

    @Mappings({
            @Mapping(target = "id", source = "gallery.id"),
            @Mapping(target = "title", source = "gallery.title"),
            @Mapping(target = "member", source = "gallery.member"),
            @Mapping(target = "createdAt", source = "gallery.createdAt"),
            @Mapping(target = "updatedAt", source = "gallery.updatedAt"),
    }
    )
    GalleryResponseDto GalleryToGalleryResponseDto(Gallery gallery);
}
