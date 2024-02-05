package com.aks.code.systemdesign.ruleengine;

import com.aks.code.systemdesign.ruleengine.rules.TaxCalculatorRule;
import com.aks.code.systemdesign.ruleengine.rules.nonresident.bracketone.BracketOneRule;
import com.aks.code.systemdesign.ruleengine.rules.nonresident.brackettwo.single.BracketTwoForRuleSingleTaxPayers;
import com.aks.code.systemdesign.ruleengine.rules.residentorcitizen.TaxRebateHealthInsuranceRule;
import com.aks.code.systemdesign.ruleengine.rules.residentorcitizen.brackettwo.notsingle.BracketTwoRuleForNotSingleTaxPayers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaxCalculatorService {
    private final List<TaxCalculatorRule> rules;

    public TaxCalculatorService() {
        rules = new ArrayList<>();
        rules.add(new BracketOneRule());
        rules.add(new com.aks.code.systemdesign.ruleengine.rules.residentorcitizen.bracketone.BracketOneRule());

        rules.add(new BracketTwoRuleForNotSingleTaxPayers());
        rules.add(new com.aks.code.systemdesign.ruleengine.rules.nonresident.brackettwo.notsingle.BracketTwoRuleForNotSingleTaxPayers());

        rules.add(new BracketTwoRuleForNotSingleTaxPayers());
        rules.add(new com.aks.code.systemdesign.ruleengine.rules.nonresident.brackettwo.notsingle.BracketTwoRuleForNotSingleTaxPayers());

        rules.add(new BracketTwoForRuleSingleTaxPayers());
        rules.add(new com.aks.code.systemdesign.ruleengine.rules.residentorcitizen.brackettwo.single.BracketTwoForRuleSingleTaxPayers());

        rules.add(new BracketTwoForRuleSingleTaxPayers());
        rules.add(new com.aks.code.systemdesign.ruleengine.rules.residentorcitizen.brackettwo.single.BracketTwoForRuleSingleTaxPayers());

        rules.add(new TaxRebateHealthInsuranceRule());
    }

    public TaxPayer processTaxReturn(TaxPayer taxPayer) {
        var engine = new TaxCalculatorRuleEngine(rules);
        return engine.calculateTax(taxPayer);
    }
}
