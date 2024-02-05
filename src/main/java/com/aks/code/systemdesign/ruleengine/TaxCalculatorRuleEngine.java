package com.aks.code.systemdesign.ruleengine;

import com.aks.code.systemdesign.ruleengine.rules.TaxCalculatorRule;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TaxCalculatorRuleEngine {
    private final List<TaxCalculatorRule> rules;

    public TaxCalculatorRuleEngine(List<TaxCalculatorRule> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }

    public TaxPayer calculateTax(TaxPayer taxPayer) {
        for (TaxCalculatorRule rule : rules) {
            if (rule.shouldRun(taxPayer)) {
                rule.evaluate(taxPayer);
            }
        }
        return taxPayer;
    }
}
