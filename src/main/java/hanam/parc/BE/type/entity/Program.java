package hanam.parc.BE.type.entity;

import hanam.parc.BE.type.etc.ApplyMethod;
import hanam.parc.BE.type.etc.DisabilityType;
import hanam.parc.BE.type.etc.ProgramStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name; // 프로그램명

    @Column(length = 200)
    private String thumbnail; // 썸네일

    @Column
    private String category; // 종목

    @Column(nullable = false)
    private Long available; // 수용인원

    @Enumerated(EnumType.STRING)
    private ProgramStatus programStatus; // 프로그램 상태

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Member manager; // 담당자

    @Column(nullable = false)
    private LocalDateTime applyEnd; // 접수 시간(day)

    @Column(nullable = false)
    private LocalDate startDate; // 시작일

    @Column(nullable = false)
    private LocalDate endDate; // 종료일

    @Column(nullable = false)
    private Long educationTime; // 교육 시간(min)

    @Column(nullable = false)
    private String location; // 장소

    @Column(nullable = false)
    private String cost; // 참가비

    @Column(nullable = false)
    private String material; // 준비물

    @Column(nullable = false)
    @Size(min = 1, max = 1000)
    private String description; // 설명

    @CreationTimestamp
    @Column(name="created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
