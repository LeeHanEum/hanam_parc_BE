package hanam.parc.BE.controller;

import hanam.parc.BE.service.FileUploadService;
import hanam.parc.BE.type.dto.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "File Upload")

public class FileUploadController {

    private final FileUploadService fileUploadService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @Operation(summary = "파일 업로드", description = "파일 업로드")
    public ResponseModel<?> upload(
            @RequestPart(value = "file", required = false) MultipartFile multipartFile
    ) {
        String url = fileUploadService.saveFile(multipartFile);
        return ResponseModel.success(url);
    }

}