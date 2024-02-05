package com.aks.code.systemdesign.ruleengine.rules.residentorcitizen;

import com.aks.code.systemdesign.ruleengine.TaxPayer;
import com.aks.code.systemdesign.ruleengine.rules.TaxCalculatorRule;

public class TaxRebateHealthInsuranceRule implements TaxCalculatorRule {
    @Override
    public void evaluate(TaxPayer taxPayer) {
        if (taxPayer.hasHealthInsurance) {
            taxPayer.taxedAmount = taxPayer.taxedAmount - (taxPayer.healthInsuranceAnnualPremium * .1);
        }
    }

    @Override
    public boolean shouldRun(TaxPayer taxPayer) {
        return taxPayer.hasHealthInsurance && taxPayer.isResidentOrCitizen;
    }
}
