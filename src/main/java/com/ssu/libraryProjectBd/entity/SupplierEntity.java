package com.ssu.libraryProjectBd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Supplier")
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, precision = 10, nullable = false)
    private BigInteger Id;

    @Column(name = "Name")
    private String name;

    public static SupplierEntity makeDefault(String name) {
        return builder()
                .name(name)
                .build();
    }

}
