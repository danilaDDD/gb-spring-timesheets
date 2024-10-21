package ru.gb.danila.timesheet.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Schema(name = "Проект")
@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
public class Project {
    @Schema(name = "Идентификатор")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Long id;

    @Schema(name = "Название")
    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
