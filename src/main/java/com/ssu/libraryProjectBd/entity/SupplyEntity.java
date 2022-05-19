package com.ssu.libraryProjectBd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Supply")
public class SupplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, precision = 10, nullable = false)
    private BigInteger Id;

    @Column(name = "Date_Of_Supply")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfSupply;

    @ManyToOne()
    @JoinColumn(name = "ID_Supplier", referencedColumnName = "id", nullable = false)
    private SupplierEntity supplierEntity;

    public static SupplyEntity makeDefault(SupplierEntity entity, Date dateOfSupply) {
        return builder()
                .supplierEntity(entity)
                .dateOfSupply(dateOfSupply)
                .build();
    }
}
