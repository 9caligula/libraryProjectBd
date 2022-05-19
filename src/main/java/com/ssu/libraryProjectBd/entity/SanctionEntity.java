package com.ssu.libraryProjectBd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Sanction")
public class SanctionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, precision = 10, nullable = false)
    private BigInteger Id;

    @Column(name = "Amount")
    private Float amount;

    @ManyToOne()
    @JoinColumn(name = "ID_Issuance_Of_Book", referencedColumnName = "id", nullable = false)
    private IssuanceOfBookEntity issuance;
}
