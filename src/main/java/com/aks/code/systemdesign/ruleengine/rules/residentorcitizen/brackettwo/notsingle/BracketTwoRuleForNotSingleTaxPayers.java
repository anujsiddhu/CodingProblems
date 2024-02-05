package com.aks.code.systemdesign.ruleengine.rules.residentorcitizen.brackettwo.notsingle;

import com.aks.code.systemdesign.ruleengine.TaxPayer;
import com.aks.code.systemdesign.ruleengine.rules.TaxCalculatorRule;

public class BracketTwoRuleForNotSingleTaxPayers implements TaxCalculatorRule {
    @Override
    public void evaluate(TaxPayer taxPayer) {
        if (taxPayer.grossIncome > 90000) {
            taxPayer.taxedAmount = taxPayer.taxedAmount + (.15 * (taxPayer.grossIncome - 90000));
        }
    }

    @Override
    public boolean shouldRun(TaxPayer taxPayer) {
        return taxPayer.isResidentOrCitizen && !taxPayer.isSingle;
    }
}
