package hanam.parc.BE.controller;

import hanam.parc.BE.service.PopUpService;
import hanam.parc.BE.type.dto.PopUpRequestDto;
import hanam.parc.BE.type.dto.PopUpResponseDto;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/popup")
@Tag(name = "PopUp", description = "popup 관리")
public class PopUpController {

    private final PopUpService popUpService;

    @PostMapping(path ="", consumes = "multipart/form-data")
    @Operation(summary = "[U] PopUp 생성", description = "PopUp 생성")
    public ResponseModel<?> createPopUp(
            PopUpRequestDto popUpRequestDto,
            @RequestPart(value = "url", required = false) MultipartFile multipartFile
    ) {
        popUpService.createPopUp(popUpRequestDto, multipartFile);
        return ResponseModel.success(true);
    }


    @GetMapping("")
    @Operation(summary = "[U] PopUp 조회", description = "PopUp 조회")
    public ResponseModel<?> getPopUp(
            @RequestParam Long id
    ) {
        PopUpResponseDto popUp = popUpService.getPopUp(id);
        return ResponseModel.success(popUp);
    }

    @GetMapping("/isShow")
    @Operation(summary = "[U] PopUp 페이징 조회", description = "PopUp 페이징 조회")
    public ResponseModel<?> getPopUpList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean isShow
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PopUpResponseDto> popUpList = popUpService.getPopUpByPageIsShow(pageable, isShow);
        return ResponseModel.success(popUpList);
    }
    @GetMapping("/page")
    @Operation(summary = "[U] PopUp 페이징 조회", description = "PopUp 페이징 조회")
    public ResponseModel<?> getPopUpList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PopUpResponseDto> popUpList = popUpService.getPopUpByPage(pageable);
        return ResponseModel.success(popUpList);
    }

    @PatchMapping("")
    @Operation(summary = "[U] PopUp 상태 수정", description = "PopUp 상태 수정")
    public ResponseModel<?> updatePopUp(
            @RequestParam Long id
    ) {
        popUpService.updatePopUp(id);
        return ResponseModel.success(true);
    }

    @DeleteMapping("")
    @Operation(summary = "[U] PopUp 삭제", description = "PopUp 삭제")
    public ResponseModel<?> deletePopUp(
            @RequestParam Long id
    ) {
        popUpService.deletePopUp(id);
        return ResponseModel.success(true);
    }

}
