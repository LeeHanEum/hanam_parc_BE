package hanam.parc.BE.controller;

import hanam.parc.BE.service.ProgramService;
import hanam.parc.BE.type.dto.ProgramRequestDto;
import hanam.parc.BE.type.dto.ProgramResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.etc.ProgramStatus;
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
@RequestMapping("/program")
@Tag(name = "Program", description = "program 관리")
public class ProgramController {

    private final ProgramService programService;

    @PostMapping("")
    @Operation(summary = "[A] Program 등록", description = "Program 등록")
    public ResponseModel<?> createProgram(
            @RequestBody ProgramRequestDto programRequestDto
    ) {
        programService.createProgram(programRequestDto);
        return ResponseModel.success(true);
    }

    @GetMapping("")
    @Operation(summary = "[U] Program 조회", description = "Program 조회")
    public ResponseModel<?> getProgram(
            @RequestParam Long id
    ) {
        ProgramResponseDto programRequestDto = programService.getProgram(id);
        return ResponseModel.success(programRequestDto);
    }

    @GetMapping("/list")
    @Operation(summary = "[U] Program 리스트 조회", description = "Program 리스트 조회")
    public ResponseModel<?> getProgramList(
    ) {
        List<ProgramResponseDto> programList = programService.getProgramList();
        return ResponseModel.success(programList);
    }

    @GetMapping("/page")
    @Operation(summary = "[U] Program 페이징 조회", description = "Program 페이징 조회")
    public ResponseModel<?> getProgramListByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProgramResponseDto> programList = programService.getProgramListByPage(pageable);
        return ResponseModel.success(programList);
    }

    @GetMapping("/my")
    @Operation(summary = "[U] 내가 신청한 Program 조회", description = "내가 신청한 Program 조회")
    public ResponseModel<?> getMyProgramList(
    ) {
        List<ProgramResponseDto> programList = programService.getMyProgramList();
        return ResponseModel.success(programList);
    }

    @GetMapping("/status")
    @Operation(summary = "[U] Program 상태별 리스트 조회", description = "Program 상태별 리스트 조회")
    public ResponseModel<?> getProgramListByStatus(
            @RequestParam ProgramStatus status
    ) {
        List<ProgramResponseDto> programList = programService.getProgramListByStatus(status);
        return ResponseModel.success(programList);
    }

    @PatchMapping("")
    @Operation(summary = "[A] Program 수정", description = "Program 수정")
    public ResponseModel<?> updateProgram(
            @RequestParam Long id,
            @RequestBody ProgramRequestDto programRequestDto
    ) {
        programService.updateProgram(id, programRequestDto);
        return ResponseModel.success(true);
    }

    @PatchMapping("/status")
    @Operation(summary = "[A] Program 상태 변경", description = "Program 상태 변경")
    public ResponseModel<?> updateProgramStatus(
            @RequestParam Long id,
            @RequestParam ProgramStatus status
    ) {
        programService.updateProgramStatus(id, status);
        return ResponseModel.success(true);
    }

    @DeleteMapping("")
    @Operation(summary = "[A] Program 삭제", description = "Program 삭제")
    public ResponseModel<?> deleteProgram(
            @RequestParam Long id
    ) {
        programService.deleteProgram(id);
        return ResponseModel.success(true);
    }

}
