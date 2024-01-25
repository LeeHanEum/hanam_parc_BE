package hanam.parc.BE.controller;

import hanam.parc.BE.service.BoardService;
import hanam.parc.BE.type.dto.BoardDto;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.etc.BoardCategory;
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
@RequestMapping("/board")
@Tag(name = "Board", description = "게시판 관리")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("")
    @Operation(summary = "[U] 게시판 생성", description = "게시판 생성")
    public ResponseModel<?> createBoard(
            @RequestBody BoardDto boardDto
    ) {
        boardService.createBoard(boardDto);
        return ResponseModel.success(true);
    }

    @GetMapping("")
    @Operation(summary = "[U] 게시판 조회", description = "게시판 조회")
    public ResponseModel<?> getBoard(
            @RequestParam Long id
    ) {
        BoardDto board = boardService.getBoard(id);
        return ResponseModel.success(board);
    }

    @GetMapping("/list")
    @Operation(summary = "[U] 게시판 리스트 조회", description = "게시판 리스트 조회")
    public ResponseModel<?> getBoardList(
    ) {
        List<BoardDto> boardList = boardService.getBoardList();
        return ResponseModel.success(boardList);
    }

    @GetMapping("/{BoardCategory}")
    @Operation(summary = "[U] 게시판 카테고리별 리스트 조회", description = "게시판 카테고리별 리스트 조회")
    public ResponseModel<?> getBoardListByCategory(
            @RequestParam BoardCategory boardCategory
    ) {
        List<BoardDto> boardList = boardService.getBoardListByCategory(boardCategory);
        return ResponseModel.success(boardList);
    }

    @GetMapping("/my")
    @Operation(summary = "[U] 내 게시판 리스트 조회", description = "내 게시판 리스트 조회")
    public ResponseModel<?> getMyBoardList(
    ) {
        List<BoardDto> boardList = boardService.getMyBoardList();
        return ResponseModel.success(boardList);
    }

    @PatchMapping("")
    @Operation(summary = "[U] 게시판 정보 수정", description = "게시판 정보 수정")
    public ResponseModel<?> updateBoard(
            @RequestParam Long id,
            @RequestBody BoardDto boardDto
    ) {
        boardService.updateBoard(id, boardDto);
        return ResponseModel.success(true);
    }

    @DeleteMapping
    @Operation(summary = "[U] 게시판 삭제", description = "게시판 삭제")
    public ResponseModel<?> deleteBoard(
            @RequestParam Long id
    ) {
        boardService.deleteBoard(id);
        return ResponseModel.success(true);
    }

}
