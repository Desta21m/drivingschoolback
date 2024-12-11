package com.example.drivingschoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.List;


@Entity
@Data
@Table(name = "vehicle_assignments")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "active_assignments",
                procedureName = "active_assignments",
                resultClasses = {VehicleAssignment.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "inactive_assignments",
                procedureName = "inactive_assignments",
                resultClasses = {VehicleAssignment.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
                }
        )
})
public class VehicleAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignmentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Vehicle vehicle;

    private Boolean status;

    @OneToMany(mappedBy = "vehicleAssignment")
    @JsonIgnore
    private List<Lesson> lessons;
}

