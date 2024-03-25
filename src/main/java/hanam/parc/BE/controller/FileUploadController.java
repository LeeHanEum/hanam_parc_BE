package hanam.parc.BE.controller;

import hanam.parc.BE.exception.FileUploadFailException;
import hanam.parc.BE.repository.BoardImageRepository;
import hanam.parc.BE.repository.PopUpRepository;
import hanam.parc.BE.repository.ProgramRepository;
import hanam.parc.BE.service.BoardService;
import hanam.parc.BE.service.FileService;
import hanam.parc.BE.service.FileUploadService;
import hanam.parc.BE.service.PopUpService;
import hanam.parc.BE.service.ProgramService;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.entity.Board;
import hanam.parc.BE.type.entity.BoardImage;
import hanam.parc.BE.type.entity.PopUp;
import hanam.parc.BE.type.entity.Program;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    private final ProgramService programService;

    private final PopUpService popUpService;

    private final BoardImageRepository boardImageRepository;

    private final ProgramRepository programRepository;

    private final FileService fileService;

    @PostMapping(path="/{boardId}", consumes = {"multipart/form-data"})
    @Operation(summary = "[A] 게시글 사진 업로드", description = "게시글 사진 업로드")
    public ResponseModel<?> boardUpload(
            @PathVariable("boardId") Long boardId,
            @RequestParam(value="image", required = false) MultipartFile multipartFile
    ) throws FileUploadFailException {
        String url = fileUploadService.saveFile(multipartFile, "boards/" + boardId);
        Board board = boardService.getBoardById(boardId);
        BoardImage boardImage = new BoardImage();
        boardImage.setBoard(board);
        boardImage.setUrl(url);
        boardImageRepository.save(boardImage);
        return ResponseModel.success(url);
    }

    @Transactional
    @DeleteMapping(path="/{boardId}")
    @Operation(summary = "[A] 게시글 사진 삭제", description = "게시글 사진 삭제")
    public ResponseModel<?> boardImageDelete(
            @RequestParam("url") String url
    ) {
        fileService.boardImageDelete(url);
        return ResponseModel.success(true);
    }

    @Transactional
    @DeleteMapping(path="/{boardId}/file")
    @Operation(summary = "[A] 게시글 파일 삭제", description = "게시글 파일 삭제")
    public ResponseModel<?> boardFileDelete(
            @RequestParam("url") String url
    ) {
        fileService.boardFileDelete(url);
        return ResponseModel.success(true);
    }

    @PostMapping(path="/{boardId}/file", consumes = {"multipart/form-data"})
    @Operation(summary = "[A] 게시글 파일 업로드", description = "게시글 파일 업로드")
    public ResponseModel<?> boardFileUpload(
            @PathVariable("boardId") Long boardId,
            @RequestParam(value="file", required = false) MultipartFile multipartFile
    ) throws FileUploadFailException {
        String url = fileUploadService.saveFile(multipartFile, "file/boards/" + boardId);
        fileService.boardFileUpload(boardId, url, multipartFile.getOriginalFilename());
        return ResponseModel.success(url);
    }



    @PostMapping(path="/program/{programId}" , consumes = {"multipart/form-data"})
    @Operation(summary = "[A] 프로그램 사진 업로드", description = "프로그램 사진 업로드")
    public ResponseModel<?> programUpload(
            @PathVariable Long programId,
            @RequestParam(value="image", required = false) MultipartFile multipartFile
    ) throws FileUploadFailException {
        String url = fileUploadService.saveFile(multipartFile, "programs/" + programId);
        Program program = programService.getProgramById(programId);
        program.setThumbnail(url);
        programRepository.save(program);
        return ResponseModel.success(url);
    }

    @PostMapping(path="/popup/{popupId}" , consumes = {"multipart/form-data"})
    @Operation(summary = "[A] 팝업 사진 업로드", description = "팝업 사진 업로드")
    public ResponseModel<?> popupUpload(
            @PathVariable Long popupId,
            @RequestParam(value="image", required = false) MultipartFile multipartFile
    ) throws FileUploadFailException {
        String url = fileUploadService.saveFile(multipartFile, "popups/" + popupId);
        fileService.popupUpload(popupId, url);
        return ResponseModel.success(url);
    }

    @PostMapping(path="/gallery/{galleryId}" , consumes = {"multipart/form-data"})
    @Operation(summary = "[A] 갤러리 사진 업로드", description = "갤러리 사진 업로드")
    public ResponseModel<?> galleryUpload(
            @PathVariable Long galleryId,
            @RequestParam(value="image", required = false) MultipartFile multipartFile
    ) throws FileUploadFailException {
        String url = fileUploadService.saveFile(multipartFile, "galleries/" + galleryId);
        fileService.galleryUpload(galleryId, url);
        return ResponseModel.success(url);
    }

}
