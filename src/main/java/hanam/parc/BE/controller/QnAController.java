package hanam.parc.BE.controller;

import hanam.parc.BE.service.QnAService;
import hanam.parc.BE.type.dto.QnARequestDto;
import hanam.parc.BE.type.dto.QnAResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
@Tag(name = "QnA", description = "QnA 관리")
public class QnAController {

    private final QnAService qnaService;

    @PostMapping("")
    @Operation(summary = "[U] QnA 생성", description = "QnA 생성")
    public ResponseModel<?> createQnA(
            @RequestBody QnARequestDto qnaRequestDto
    ) {
        qnaService.createQnA(qnaRequestDto);
        return ResponseModel.success(true);
    }

    @GetMapping("")
    @Operation(summary = "[U] QnA 조회", description = "QnA 조회")
    public ResponseModel<?> getQnA(
            @RequestParam Long id
    ) {
        QnAResponseDto qna = qnaService.getQnA(id);
        return ResponseModel.success(qna);
    }

    @GetMapping("/list")
    @Operation(summary = "[U] QnA 리스트 조회", description = "QnA 리스트 조회")
    public ResponseModel<?> getQnAList(
    ) {
        List<QnAResponseDto> qnaList = qnaService.getQnAList();
        return ResponseModel.success(qnaList);
    }

    @GetMapping("/page")
    @Operation(summary = "[U] QnA 페이지 조회", description = "QnA 페이지 조회")
    public ResponseModel<?> getQnAPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<QnAResponseDto> qnaList = qnaService.getQnAPage(pageable);
        return ResponseModel.success(qnaList);
    }

    @GetMapping("/my")
    @Operation(summary = "[U] 내 QnA 조회", description = "내 QnA 조회")
    public ResponseModel<?> getMyQnA(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<QnAResponseDto> qnaList = qnaService.getMyQnA(pageable);
        return ResponseModel.success(qnaList);
    }

    @PatchMapping("/update")
    @Operation(summary = "[U] QnA 수정", description = "QnA 수정")
    public ResponseModel<?> updateQnA(
            @RequestParam Long id,
            @RequestBody QnARequestDto qnaRequestDto
    ) {
        qnaService.updateQnA(id, qnaRequestDto);
        return ResponseModel.success(true);
    }

    @DeleteMapping("")
    @Operation(summary = "[U] QnA 삭제", description = "QnA 삭제")
    public ResponseModel<?> deleteQnA(
            @RequestParam Long id
    ) {
        qnaService.deleteQnA(id);
        return ResponseModel.success(true);
    }

    @PostMapping("/answer")
    @Operation(summary = "[A] QnA 답변", description = "QnA 답변")
    public ResponseModel<?> answerQnA(
            @RequestParam Long id,
            @RequestParam String answer
    ) {
        qnaService.answerQnA(id, answer);
        return ResponseModel.success(true);
    }

}
