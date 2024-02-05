package com.aks.code.systemdesign.ruleengine.rules.nonresident.brackettwo.notsingle;

import com.aks.code.systemdesign.ruleengine.TaxPayer;
import com.aks.code.systemdesign.ruleengine.rules.TaxCalculatorRule;

public class BracketTwoRuleForNotSingleTaxPayers implements TaxCalculatorRule {
    @Override
    public void evaluate(TaxPayer taxPayer) {
        if (taxPayer.grossIncome > 100000) {
            taxPayer.taxedAmount = taxPayer.taxedAmount + (.2 * (taxPayer.grossIncome) - 100000);
        }
    }

    @Override
    public boolean shouldRun(TaxPayer taxPayer) {
        return !taxPayer.isResidentOrCitizen && !taxPayer.isSingle;
    }
}
