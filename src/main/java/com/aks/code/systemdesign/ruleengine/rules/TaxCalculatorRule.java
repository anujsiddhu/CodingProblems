package com.aks.code.systemdesign.ruleengine.rules;

import com.aks.code.systemdesign.ruleengine.TaxPayer;

public interface TaxCalculatorRule {

    void evaluate(TaxPayer taxPayer);

    boolean shouldRun(TaxPayer taxPayer);
}
