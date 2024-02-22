package hanam.parc.BE.utils;

import hanam.parc.BE.exception.ImageCompressionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

@Component
@Slf4j
public class ImageCompressionUtil {

    public static void compressImage(String filePath, float quality) {
        try {
            File inputFile = new File(filePath);
            BufferedImage image = ImageIO.read(inputFile);
            OutputStream os = new FileOutputStream(inputFile);

            String formatName = FilenameUtils.getExtension(inputFile.getName());
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(formatName);
            ImageWriter writer = writers.next();

            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);

            ImageIO.write(image, formatName, os);

            os.close();
        } catch (Exception e) {
            throw new ImageCompressionException("이미지 압축 실패 : " + filePath);
        }
    }

}
