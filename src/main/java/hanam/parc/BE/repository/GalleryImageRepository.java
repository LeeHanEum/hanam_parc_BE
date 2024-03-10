package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Gallery;
import hanam.parc.BE.type.entity.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryImageRepository extends JpaRepository<GalleryImage, Long> {

    GalleryImage findFirstByGalleryId(Long galleryId);

    List<GalleryImage> findAllByGallery(Gallery gallery);

}
