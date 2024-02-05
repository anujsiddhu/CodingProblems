package com.aks.code.systemdesign.ruleengine;

// https://blog.devgenius.io/open-closed-principle-and-rule-engine-design-pattern-904c784501e5
public class TaxCalculator {
    public static void main(String[] args) {
        TaxPayer taxPayer = new TaxPayer();
        taxPayer.isResidentOrCitizen = false;
        taxPayer.grossIncome = 300000;
        taxPayer.isSingle = true;
        taxPayer.healthInsuranceAnnualPremium = 3000;
        taxPayer.hasHealthInsurance = true;
        TaxCalculatorService taxCalculatorService = new TaxCalculatorService();
        taxPayer = taxCalculatorService.processTaxReturn(taxPayer);
        System.out.println("taxPayer :- " + taxPayer + "\nTotal tax = " + taxPayer.taxedAmount);
    }
}
