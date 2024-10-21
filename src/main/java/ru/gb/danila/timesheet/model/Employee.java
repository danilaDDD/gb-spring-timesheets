package ru.gb.danila.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "second_name", nullable = false, length = 50)
    private String secondName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
       name = "employee_project",
       joinColumns = @JoinColumn(name = "project_id"),
       inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Project> projects;
}
