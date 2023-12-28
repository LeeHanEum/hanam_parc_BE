package hanam.parc.BE.repository;

import hanam.parc.BE.type.entity.Member;
import hanam.parc.BE.type.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByMember(Member member);
}
