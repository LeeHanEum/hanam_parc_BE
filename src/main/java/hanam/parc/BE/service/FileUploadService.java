package hanam.parc.BE.service;

import hanam.parc.BE.exception.FileUploadFailException;
import hanam.parc.BE.handler.FileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileHandler fileHandler;

    @Value("${resource.file.url}")
    private String fileURL;

    public String saveFile(MultipartFile multipartFile, String path) throws FileUploadFailException {
        String realFilename = fileHandler.saveFile(multipartFile, path);
        return fileURL + "/" + realFilename;
    }
}
