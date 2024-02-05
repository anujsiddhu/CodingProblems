package com.aks.code.systemdesign.librarymanagement;

import lombok.Data;

@Data
public class FineDetail {
    private int issueTrxId;
    private int amount;
    private String paymentMode;
}
