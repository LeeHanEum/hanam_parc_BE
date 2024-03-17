package hanam.parc.BE.controller;

import hanam.parc.BE.service.ApplicationService;
import hanam.parc.BE.type.dto.ApplicationRequestDto;
import hanam.parc.BE.type.dto.ApplicationResponseDto;
import hanam.parc.BE.type.dto.ResponseModel;
import hanam.parc.BE.type.etc.ApplicationStatus;
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
@RequestMapping("/application")
@Tag(name = "Application", description = "application 관리")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("")
    @Operation(summary = "[U] Application 등록", description = "Application 등록")
    public ResponseModel<?> createApplication(
            @RequestBody ApplicationRequestDto applicationRequestDto
    ) {
        try {
            applicationService.createApplication(applicationRequestDto);
            return ResponseModel.success(true);
        }catch (Exception e) {
            return ResponseModel.fail("400", e.getMessage());
        }
    }

    @GetMapping("")
    @Operation(summary = "[U] Application 조회", description = "Application 조회")
    public ResponseModel<?> getApplication(
            @RequestParam Long id
    ) {
        ApplicationResponseDto applicationResponseDto = applicationService.getApplication(id);
        return ResponseModel.success(applicationResponseDto);
    }

    @GetMapping("/my")
    @Operation(summary = "[U] 내가 신청한 Application 조회", description = "내가 신청한 Application 조회")
    public ResponseModel<?> getMyApplication(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ApplicationResponseDto> applicationResponseDtoList = applicationService.getMyApplication(pageable);
        return ResponseModel.success(applicationResponseDtoList);
    }

    @GetMapping("/list")
    @Operation(summary = "[A] Application 리스트 조회", description = "Application 리스트 조회")
    public ResponseModel<?> getApplicationList() {
        List<ApplicationResponseDto> applicationRequestDtoList = applicationService.getApplicationList();
        return ResponseModel.success(applicationRequestDtoList);
    }

    @GetMapping("/page")
    @Operation(summary = "[A] Application 페이징 조회", description = "Application 페이징 조회")
    public ResponseModel<?> getApplicationListByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ApplicationResponseDto> applicationDtoList = applicationService.getApplicationListByPage(pageable);
        return ResponseModel.success(applicationDtoList);
    }

    @GetMapping("/program")
    @Operation(summary = "[A] Program에 대한 Application 리스트 조회", description = "Program에 대한 Application 리스트 조회")
    public ResponseModel<?> getApplicationListByProgram(
            @RequestParam Long programId
    ) {
        List<ApplicationResponseDto> applicationResponseDtoList = applicationService.getApplicationListByProgram(programId);
        return ResponseModel.success(applicationResponseDtoList);
    }

    @GetMapping("/program/count")
    @Operation(summary = "[A] Program에 대한 Application 수 조회", description = "Program에 대한 Application 수 조회")
    public ResponseModel<?> getApplicationCountByProgram(
            @RequestParam Long programId
    ) {
        Long applicationCount = applicationService.getApplicationCountByProgram(programId);
        return ResponseModel.success(applicationCount);
    }

    @PatchMapping("")
    @Operation(summary = "[U] Application 수정", description = "Application 수정")
    public ResponseModel<?> updateApplication(
            @RequestParam Long id,
            @RequestBody ApplicationRequestDto applicationRequestDto
    ) {
        applicationService.updateApplication(id, applicationRequestDto);
        return ResponseModel.success(true);
    }

    @PatchMapping("/status")
    @Operation(summary = "[A] Application 상태 변경", description = "Application 상태 변경")
    public ResponseModel<?> updateApplicationStatus(
            @RequestParam Long id,
            @RequestParam ApplicationStatus status
    ) {
        applicationService.updateApplicationStatus(id, status);
        return ResponseModel.success(true);
    }

    @DeleteMapping("")
    @Operation(summary = "[U] Application 취소", description = "Application 취소")
    public ResponseModel<?> deleteApplication(
            @RequestParam Long id
    ) {
        applicationService.deleteApplication(id);
        return ResponseModel.success(true);
    }

}
