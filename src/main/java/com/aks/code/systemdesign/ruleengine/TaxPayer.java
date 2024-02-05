package com.aks.code.systemdesign.ruleengine;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaxPayer {
    public double grossIncome;
    public boolean isSingle;
    public boolean isResidentOrCitizen;
    public boolean hasHealthInsurance;
    public boolean hasBusiness;
    public boolean atLossInBusiness;
    public double taxedAmount;
    public double healthInsuranceAnnualPremium;
}
