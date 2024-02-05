package com.aks.code.systemdesign.librarymanagement;

import lombok.Data;

import java.util.List;

@Data
public class Author {
    private String name;
    private List<Book> books;
}
