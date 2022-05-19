package com.ssu.libraryProjectBd.entity;

import com.ssu.libraryProjectBd.entity.view.AuthorHasBooksView;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Issuance_Of_Book")
public class IssuanceOfBookEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, precision = 10, nullable = false)
    private BigInteger id;

    @ManyToOne()
    @JoinColumn(name = "ID_User", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne()
    @JoinColumn(name = "Book_Name", referencedColumnName = "book_name", nullable = false)
    private AuthorHasBooksView book;

    @Column(name = "Date_Issue")
    private Date dateIssue;

    @Column(name = "Return_Date")
    private Date returnDate;

    @Column(name = "Actual_Return_Date")
    private Date actualReturnDate;

    @SneakyThrows
    public String getReturnDate() {
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT, Locale.TAIWAN);
        return dateInstance.format(returnDate);
    }

    public static IssuanceOfBookEntity makeDefault(UserEntity user, AuthorHasBooksView book) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 14);
        dt = c.getTime();
        return builder()
                .userEntity(user)
                .book(book)
                .dateIssue(new Date())
                .returnDate(dt)
                .build();
    }
}
