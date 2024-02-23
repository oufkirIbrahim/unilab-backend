package com.m2i.unilabmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer SupplierId;

    private String name;
    private String address;
    private String email;
    private String phone;
    private String fax;
    private String website;
    private String rib;
    private String bankName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "responsible_person_id",
            referencedColumnName = "person_id"
    )
    private User responsible;


}
