package com.aks.code.systemdesign.librarymanagement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookItem {
    private int rackNumber;
    private Book book;
}
