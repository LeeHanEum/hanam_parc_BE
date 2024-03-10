package hanam.parc.BE.controller;

import hanam.parc.BE.service.GalleryService;
import hanam.parc.BE.type.dto.GalleryImageDto;
import hanam.parc.BE.type.dto.GalleryRequestDto;
import hanam.parc.BE.type.dto.GalleryResponseDto;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gallery")
@Tag(name = "Gallery", description = "자료실 관리")
public class GalleryController {

    private final GalleryService galleryService;

    @PostMapping("/create")
    @Operation(summary = "[A] 앨범 생성", description = "앨범 생성")
    public ResponseModel<?> createGallery(
            @RequestBody GalleryRequestDto galleryRequestDto
    ) {
        Long id = galleryService.createGallery(galleryRequestDto);
        return ResponseModel.success(id);
    }

    @GetMapping("")
    @Operation(summary = "[U] 앨범 조회", description = "앨범 조회")
    public ResponseModel<?> getGallery(
            @RequestParam Long id
    ) {
        GalleryResponseDto gallery = galleryService.getGallery(id);
        return ResponseModel.success(gallery);
    }

    @GetMapping("/page")
    @Operation(summary = "[U] 앨범 페이징 조회", description = "앨범 페이징 조회")
    public ResponseModel<?> getGalleryList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GalleryResponseDto> galleryList = galleryService.getGalleryPage(pageable);
        return ResponseModel.success(galleryList);
    }

    @GetMapping("/image")
    @Operation(summary = "[U] 앨범 이미지 조회", description = "앨범 이미지 조회")
    public ResponseModel<?> getGalleryImage(
            @RequestParam Long id
    ) {
        GalleryImageDto galleryImage = galleryService.getGalleryImage(id);
        return ResponseModel.success(galleryImage);
    }

    @PatchMapping("/update")
    @Operation(summary = "[U] 앨범 수정", description = "앨범 수정")
    public ResponseModel<?> updateGallery(
            @RequestParam Long id,
            @RequestBody GalleryRequestDto galleryRequestDto
    ) {
        galleryService.updateGallery(id, galleryRequestDto);
        return ResponseModel.success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "[D] 앨범 삭제", description = "앨범 삭제")
    public ResponseModel<?> deleteGallery(
            @RequestParam Long id
    ) {
        galleryService.deleteGallery(id);
        return ResponseModel.success(true);
    }

}
