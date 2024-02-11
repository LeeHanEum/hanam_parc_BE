package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.BoardRequestDto;
import hanam.parc.BE.type.dto.BoardResponseDto;
import hanam.parc.BE.type.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "board.id"),
            @Mapping(target = "title", source = "board.title"),
            @Mapping(target = "content", source = "board.content"),
            @Mapping(target = "writer", source = "board.member"),
            @Mapping(target = "createdAt", source = "board.createdAt"),
            @Mapping(target = "updatedAt", source = "board.updatedAt"),
            @Mapping(target = "boardCategory", source = "board.boardCategory")
    })
    BoardResponseDto BoardToBoardResponseDto(Board board);

    @Mappings({
            @Mapping(target = "title", source = "boardRequestDto.title"),
            @Mapping(target = "content", source = "boardRequestDto.content"),
            @Mapping(target = "boardCategory", source = "boardRequestDto.boardCategory"),
    })
    Board BoardRequestDtoToBoard(BoardRequestDto boardRequestDto);

}
