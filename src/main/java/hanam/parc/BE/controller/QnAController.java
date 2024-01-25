package hanam.parc.BE.controller;

import hanam.parc.BE.service.QnAService;
import hanam.parc.BE.type.dto.QnARequestDto;
import hanam.parc.BE.type.dto.QnAResponseDto;
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
@RequestMapping("/qna")
@Tag(name = "QnA", description = "QnA 관리")
public class QnAController {

    private final QnAService qnaService;

    @Operation(summary = "[U] QnA 생성", description = "QnA 생성")
    @PostMapping("")
    public ResponseModel<?> createQnA(
            @RequestBody QnARequestDto qnaRequestDto
    ) {
        qnaService.createQnA(qnaRequestDto);
        return ResponseModel.success(true);
    }

    @Operation(summary = "[U] QnA 조회", description = "QnA 조회")
    @GetMapping("")
    public ResponseModel<?> getQnA(
            @RequestParam Long id
    ) {
        QnAResponseDto qna = qnaService.getQnA(id);
        return ResponseModel.success(qna);
    }

    @Operation(summary = "[U] QnA 리스트 조회", description = "QnA 리스트 조회")
    @GetMapping("/list")
    public ResponseModel<?> getQnAList(
    ) {
        List<QnAResponseDto> qnaList = qnaService.getQnAList();
        return ResponseModel.success(qnaList);
    }

    @Operation(summary = "[U] 내 QnA 조회", description = "내 QnA 조회")
    @GetMapping("/my")
    public ResponseModel<?> getMyQnA() {
        List<QnAResponseDto> qnaList = qnaService.getMyQnA();
        return ResponseModel.success(qnaList);
    }

    @Operation(summary = "[U] QnA 수정", description = "QnA 수정")
    @PatchMapping("/update")
    public ResponseModel<?> updateQnA(
            @RequestParam Long id,
            @RequestBody QnARequestDto qnaRequestDto
    ) {
        qnaService.updateQnA(id, qnaRequestDto);
        return ResponseModel.success(true);
    }

    @Operation(summary = "[U] QnA 삭제", description = "QnA 삭제")
    @DeleteMapping("")
    public ResponseModel<?> deleteQnA(
            @RequestParam Long id
    ) {
        qnaService.deleteQnA(id);
        return ResponseModel.success(true);
    }

    @Operation(summary = "[U] QnA 답변", description = "QnA 답변")
    @PostMapping("/answer")
    public ResponseModel<?> answerQnA(
            @RequestParam Long id,
            @RequestParam String answer
    ) {
        qnaService.answerQnA(id, answer);
        return ResponseModel.success(true);
    }

}