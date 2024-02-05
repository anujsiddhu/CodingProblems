package com.aks.code.systemdesign.ruleengine.rules.residentorcitizen.brackettwo.single;

import com.aks.code.systemdesign.ruleengine.TaxPayer;
import com.aks.code.systemdesign.ruleengine.rules.TaxCalculatorRule;

public class BracketTwoForRuleSingleTaxPayers implements TaxCalculatorRule {
    @Override
    public void evaluate(TaxPayer taxPayer) {
        if (taxPayer.grossIncome > 120000) {
            taxPayer.taxedAmount = taxPayer.taxedAmount + (.2 * (taxPayer.grossIncome - 120000));
        }
    }

    @Override
    public boolean shouldRun(TaxPayer taxPayer) {
        return taxPayer.isResidentOrCitizen && taxPayer.isSingle;
    }
}
