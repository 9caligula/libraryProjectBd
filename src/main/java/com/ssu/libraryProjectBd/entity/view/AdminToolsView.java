package com.ssu.libraryProjectBd.entity.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Immutable
@Table(name = "Admin_tools_view")
@Subselect("select * from Admin_tools_view")
public class AdminToolsView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, precision = 10, nullable = false)
    private BigInteger id;

    private String bookName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfSupply;

    private String nameSupplier;

    private Integer quantity;

    public static AdminToolsView makeDefault(String bookName, Date dateOfSupply, String nameSupplier, Integer quantity){
        return builder()
                .bookName(bookName)
                .dateOfSupply(dateOfSupply)
                .nameSupplier(nameSupplier)
                .quantity(quantity)
                .build();
    }
}
