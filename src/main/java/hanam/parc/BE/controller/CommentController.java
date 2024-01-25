package hanam.parc.BE.controller;

import hanam.parc.BE.service.CommentService;
import hanam.parc.BE.type.dto.CommentRequestDto;
import hanam.parc.BE.type.dto.CommentResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Tag(name = "Comment", description = "Comment 관리")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "[U] Comment 생성", description = "Comment 생성")
    @PostMapping("")
    public ResponseModel<?> createComment(
            @RequestParam Long boardId,
            @RequestParam(required = false) Long commentId,
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        commentService.createComment(boardId, commentId, commentRequestDto);
        return ResponseModel.success(true);
    }

    @Operation(summary = "[U] Comment 조회", description = "Comment 조회")
    @GetMapping("")
    public ResponseModel<?> getComment(
            @RequestParam Long id
    ) {
        CommentResponseDto comment = commentService.getComment(id);
        return ResponseModel.success(comment);
    }

    @Operation(summary = "[U] 댓글 및 답글 조회", description = "댓글 및 답글 조회")
    @GetMapping("/parent-child")
    public ResponseModel<?> getParentChildComment(
            @RequestParam Long id
    ) {
        List<CommentResponseDto> commentList = commentService.getAllCommentByParent(id);
        return ResponseModel.success(commentList);
    }

    @Operation(summary = "[U] 내 Comment 조회", description = "내 Comment 조회")
    @GetMapping("/my")
    public ResponseModel<?> getMyComment() {
        List<CommentResponseDto> commentList = commentService.getMyComment();
        return ResponseModel.success(commentList);
    }

    @Operation(summary = "[U] board 기반 댓글 조회", description = "board 기반 댓글 조회")
    @GetMapping("/board")
    public ResponseModel<?> getCommentByBoard(
            @RequestParam Long boardId
    ) {
        List<CommentResponseDto> commentList = commentService.getCommentByBoard(boardId);
        return ResponseModel.success(commentList);
    }


    @Operation(summary = "[U] Comment 수정", description = "Comment 수정")
    @PatchMapping("")
    public ResponseModel<?> updateComment(
            @RequestParam Long id,
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        commentService.updateComment(id, commentRequestDto);
        return ResponseModel.success(true);
    }

    @Operation(summary = "[U] Comment 삭제", description = "Comment 삭제")
    @DeleteMapping("")
    public ResponseModel<?> deleteComment(
            @RequestParam Long id
    ) {
        commentService.deleteComment(id);
        return ResponseModel.success(true);
    }

}
