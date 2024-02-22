package hanam.parc.BE.controller;

import hanam.parc.BE.exception.FileUploadFailException;
import hanam.parc.BE.repository.BoardImageRepository;
import hanam.parc.BE.service.BoardService;
import hanam.parc.BE.service.FileUploadService;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.BoardImage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Tag(name = "File", description = "파일 업로드 관리")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    private final BoardService boardService;

    private final BoardImageRepository boardImageRepository;

    @PostMapping(path="/{boardId}", consumes = {"multipart/form-data"})
    @Operation(summary = "[U] 게시글 사진 업로드", description = "게시글 사진 업로드")
    public ResponseModel<?> boardUpload(
            @PathVariable("boardId") Long boardId,
            @RequestParam(value = "file") MultipartFile multipartFile
    ) throws FileUploadFailException {
        String url = fileUploadService.saveFile(multipartFile, "boards/" + boardId);
        Board board = boardService.getBoardById(boardId);
        BoardImage boardImage = new BoardImage();
        boardImage.setBoard(board);
        boardImage.setUrl(url);
        boardImageRepository.save(boardImage);
        return ResponseModel.success(url);
    }



}
