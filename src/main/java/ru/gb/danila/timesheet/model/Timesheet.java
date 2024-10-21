package ru.gb.danila.timesheet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "timesheet")
@Data
@NoArgsConstructor
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "project_id", nullable = false)
    private Long projectId;
    @Column(name = "minutes", nullable = false)
    private int minutes;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;
}
