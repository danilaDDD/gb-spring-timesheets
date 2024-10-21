package ru.gb.danila.timesheet.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Schema(name = "Сотрудник")
@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee {
    @Schema(name = "Идентификатор")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Schema(name = "Имя")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Schema(name = "Фамилия")
    @Column(name = "second_name", nullable = false, length = 50)
    private String secondName;

    @Schema(name = "Проекты")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
       name = "employee_project",
       joinColumns = @JoinColumn(name = "project_id"),
       inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Project> projects;
}
