package hanam.parc.BE.service;

import hanam.parc.BE.repository.GalleryImageRepository;
import hanam.parc.BE.repository.GalleryRepository;
import hanam.parc.BE.repository.PopUpRepository;
import hanam.parc.BE.type.entity.Gallery;
import hanam.parc.BE.type.entity.GalleryImage;
import hanam.parc.BE.type.entity.PopUp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final GalleryService galleryService;

    private final GalleryRepository galleryRepository;

    private final GalleryImageRepository galleryImageRepository;

    private final PopUpRepository popUpRepository;

    public void popupUpload(Long popupId,  String url) {
        PopUp popUp = popUpRepository.findById(popupId).orElseThrow();
        popUp.setUrl(url);
        popUpRepository.save(popUp);
    }

    @Transactional
    public void galleryUpload(Long galleryId,  String url) {
        Gallery gallery = galleryService.findGalleryById(galleryId);
        GalleryImage galleryImage = new GalleryImage();
        galleryImage.setGallery(gallery);
        galleryImage.setUrl(url);
        galleryImageRepository.save(galleryImage);
        if (gallery.getThumbnail() == null) {
            gallery.setThumbnail(url);
            galleryRepository.save(gallery);
        }
    }

}
