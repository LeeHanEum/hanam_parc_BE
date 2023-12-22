package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.BoardDto;
import hanam.parc.BE.type.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "boardDto.id"),
            @Mapping(target = "title", source = "boardDto.title"),
            @Mapping(target = "member", source = "boardDto.member"),
            @Mapping(target = "category", source = "boardDto.category")
    })
    Board BoardDtoToBoard(BoardDto boardDto);

    @Mappings({
            @Mapping(target = "id", source = "board.id"),
            @Mapping(target = "title", source = "board.title"),
            @Mapping(target = "member", source = "board.member"),
            @Mapping(target = "category", source = "board.category")
    })
    BoardDto BoardToBoardDto(Board board);

}