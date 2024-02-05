package com.aks.code.systemdesign.ruleengine.rules.residentorcitizen.bracketone;

import com.aks.code.systemdesign.ruleengine.TaxPayer;
import com.aks.code.systemdesign.ruleengine.rules.TaxCalculatorRule;

public class BracketOneRule implements TaxCalculatorRule {
    @Override
    public void evaluate(TaxPayer taxPayer) {
        if (taxPayer.grossIncome < 40000) taxPayer.taxedAmount = 0;
        else {
            taxPayer.taxedAmount = taxPayer.taxedAmount + ((taxPayer.grossIncome - 40000) * .1);
        }
    }

    @Override
    public boolean shouldRun(TaxPayer taxPayer) {
        return taxPayer.isResidentOrCitizen;
    }
}
