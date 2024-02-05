package com.aks.code.systemdesign.ruleengine.rules.nonresident.brackettwo.single;

import com.aks.code.systemdesign.ruleengine.TaxPayer;
import com.aks.code.systemdesign.ruleengine.rules.TaxCalculatorRule;

public class BracketTwoForRuleSingleTaxPayers implements TaxCalculatorRule {
    @Override
    public void evaluate(TaxPayer taxPayer) {
        if (taxPayer.grossIncome > 70000) {
            taxPayer.taxedAmount = taxPayer.taxedAmount + (.15 * (taxPayer.grossIncome - 70000));
        }
    }

    @Override
    public boolean shouldRun(TaxPayer taxPayer) {
        return !taxPayer.isResidentOrCitizen && taxPayer.isSingle;
    }
}
