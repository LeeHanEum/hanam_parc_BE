package hanam.parc.BE.mapper;

import hanam.parc.BE.type.dto.CommentRequestDto;
import hanam.parc.BE.type.dto.CommentResponseDto;
import hanam.parc.BE.type.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mappings({
            @Mapping(target = "content", source = "commentRequestDto.content")
    })
    Comment CommentRequestDtoToComment(CommentRequestDto commentRequestDto);

    @Mappings({
            @Mapping(target = "id", source = "comment.id"),
            @Mapping(target = "content", source = "comment.content"),
            @Mapping(target = "writer", source = "comment.member.name"),
            @Mapping(target = "createdAt", source = "comment.createdAt"),
            @Mapping(target = "updatedAt", source = "comment.updatedAt")

    })
    CommentResponseDto CommentToCommentResponseDto(Comment comment);
}
