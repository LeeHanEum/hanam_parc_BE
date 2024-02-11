package hanam.parc.BE.service;

import hanam.parc.BE.handler.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileHandler fileHandler;

    @Value("${resource.file.url}")
    private String fileURL;

    public String saveFile(MultipartFile multipartFile){
        String realFilename = fileHandler.saveFile(multipartFile);
        return fileURL + realFilename;
    }
}
