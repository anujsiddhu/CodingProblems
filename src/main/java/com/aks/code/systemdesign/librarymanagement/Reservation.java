package com.aks.code.systemdesign.librarymanagement;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservation {
    private int transactionId;
    private Book book;
    private Account member;
    private Account issuer;
    private ReservationStatus status;
    private LocalDate issueDate;
    private LocalDate returnDate;
}
