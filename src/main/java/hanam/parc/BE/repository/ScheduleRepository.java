package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
