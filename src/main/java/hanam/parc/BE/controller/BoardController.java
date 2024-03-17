package hanam.parc.BE.controller;

import hanam.parc.BE.service.BoardService;
import hanam.parc.BE.type.dto.BoardImageDto;
import hanam.parc.BE.type.dto.BoardRequestDto;
import hanam.parc.BE.type.dto.BoardResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.etc.BoardCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Tag(name = "Board", description = "게시판 관리")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    @Operation(summary = "[U] 게시판 생성", description = "게시판 생성")
    public ResponseModel<?> createBoard(
            @RequestBody BoardRequestDto boardRequestDto
    ) {
        Long id = boardService.createBoard(boardRequestDto);
        return ResponseModel.success(id);
    }

    @GetMapping("")
    @Operation(summary = "[U] 게시판 조회", description = "게시판 조회")
    public ResponseModel<?> getBoard(
            @RequestParam Long id
    ) {
        BoardResponseDto board = boardService.getBoard(id);
        return ResponseModel.success(board);
    }

    @GetMapping("/image")
    @Operation(summary = "[U] 게시판 이미지 조회", description = "게시판 이미지 조회")
    public ResponseModel<?> getBoardImage(
            @RequestParam Long id
    ) {
        BoardImageDto boardImage = boardService.getBoardImage(id);
        return ResponseModel.success(boardImage);
    }

    @GetMapping("/list")
    @Operation(summary = "[U] 게시판 리스트 조회", description = "게시판 리스트 조회")
    public ResponseModel<?> getBoardList() {
        List<BoardResponseDto> boardList = boardService.getBoardList();
        return ResponseModel.success(boardList);
    }

    @GetMapping("/{boardCategory}")
    @Operation(summary = "[U] 게시판 카테고리별 리스트 조회", description = "게시판 카테고리별 리스트 조회")
    public ResponseModel<?> getBoardListByCategory(
            @PathVariable BoardCategory boardCategory
    ) {
        List<BoardResponseDto> boardList = boardService.getBoardListByCategory(boardCategory);
        return ResponseModel.success(boardList);
    }

    @GetMapping("/{boardCategory}/page")
    @Operation(summary = "[U] 게시판 카테고리별 페이징 리스트 조회", description = "게시판 카테고리별 페이징 리스트 조회")
    public ResponseModel<?> getBoardPageByCategory(
            @PathVariable BoardCategory boardCategory,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BoardResponseDto> boardList = boardService.getBoardPageByCategory(boardCategory, pageable);
        return ResponseModel.success(boardList);
    }

    @GetMapping("/page")
    @Operation(summary = "[U] 게시판 페이징 리스트 조회", description = "게시판 페이징 리스트 조회")
    public ResponseModel<?> getBoardListByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BoardResponseDto> boardList = boardService.getBoardListByPage(pageable);
        return ResponseModel.success(boardList);
    }

    @GetMapping("/my")
    @Operation(summary = "[U] 내 게시판 리스트 조회", description = "내 게시판 리스트 조회")
    public ResponseModel<?> getMyBoardList() {
        List<BoardResponseDto> boardList = boardService.getMyBoardList();
        return ResponseModel.success(boardList);
    }

    @PatchMapping("/update")
    @Operation(summary = "[U] 게시판 정보 수정", description = "게시판 정보 수정")
    public ResponseModel<?> updateBoard(
            @RequestParam Long id,
            @RequestBody BoardRequestDto boardRequestDto
    ) {
        boardService.updateBoard(id, boardRequestDto);
        return ResponseModel.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "[U] 게시판 삭제", description = "게시판 삭제")
    public ResponseModel<?> deleteBoard(
            @RequestParam Long id
    ) {
        boardService.deleteBoard(id);
        return ResponseModel.success(true);
    }

}
