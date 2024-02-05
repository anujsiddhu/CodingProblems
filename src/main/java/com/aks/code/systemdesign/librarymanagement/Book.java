package com.aks.code.systemdesign.librarymanagement;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Book {
    private int id;
    private List<Author> authors;
    private String title;
    private String subject;
    private long numberOfPages;
    private BookFormat bookFormat;
    private LocalDate publicationDate;
}
