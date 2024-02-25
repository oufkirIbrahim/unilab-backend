package com.m2i.unilabmanagerbackend.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "material")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "materialId")
public class Material {

    @Id
    @Column(name = "material_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer materialId;

    @Column(name = "inventory_number")
    private String inventoryNumber;

    private String type;

    @Column(name = "acquisition_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date acquisitionDate;

    @Column(name = "assignment_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date assignmentDate;

    @ManyToOne
    @JoinColumn(
            name = "responsible_person_id",
            referencedColumnName = "person_id"
    )
    private User responsiblePerson;

    @ManyToOne
    @JoinColumn(
            name = "supplier_id",
            referencedColumnName = "supplier_id"
    )
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "laboratory_id",
            referencedColumnName = "laboratory_id"
    )
    private Laboratory laboratory;

    @Column(name = "lab_assignment_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date labAssignmentDate;

    @OneToMany(mappedBy = "material")
    @Column(insertable = false,updatable = false)
    @JsonIgnore
    private List<MaterialOrder> materialOrders;

}
