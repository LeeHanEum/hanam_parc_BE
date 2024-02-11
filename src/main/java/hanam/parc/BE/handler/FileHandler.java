package hanam.parc.BE.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

@Component
public class FileHandler {

    @Value("${resource.file.path}")
    private String filePath;

    @Value("${resource.file.allow-extension}")
    private String[] allowExtensions;

    public String saveFile(MultipartFile multipartFile){
        String originalFilename = multipartFile.getOriginalFilename();
        if (!validateFilename(originalFilename)) {
            throw new RuntimeException("허용되지 않은 파일명 : " + originalFilename);
        }
        String extension = FilenameUtils.getExtension(originalFilename);
        if (!validateExtension(extension)) {
            throw new RuntimeException("허용되지 않은 확장자 : " + originalFilename);
        }
        String newFilename = System.nanoTime() + "_" + UUID.randomUUID() + "." + extension;
        String dirPath = filePath;
        String destPath = dirPath + File.separator + newFilename;
        File dir = new File(dirPath);
        File file = new File(destPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            String os = System.getProperty("os.name").toLowerCase();
            multipartFile.transferTo(file);
            if (os.contains("win")) {
                file.setReadable(true);
                file.setWritable(false);
                file.setExecutable(false);
            } else {
                Runtime.getRuntime().exec("chmod 400 " + destPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }
        return newFilename;
    }

    private boolean validateExtension(String extension) {
        for (String allowExtension : allowExtensions) {
            if (allowExtension.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateFilename(String fileName) {
        return fileName != null && !fileName.isEmpty();
    }
}
