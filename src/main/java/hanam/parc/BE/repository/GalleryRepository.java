package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    Page<Gallery> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
