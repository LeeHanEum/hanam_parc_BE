package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.PopUp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopUpRepository extends JpaRepository<PopUp, Long> {

    Page<PopUp> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<PopUp> findAllByIsShowOrderByCreatedAtDesc(boolean isShow, Pageable pageable);
}
