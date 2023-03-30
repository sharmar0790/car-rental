package io.rental.rule.impl;

import io.rental.Car;
import io.rental.Criteria;
import io.rental.rule.IRule;
import io.rental.rule.IRuleEvaluator;

import java.util.ArrayList;
import java.util.List;

public class RuleEvaluator implements IRuleEvaluator {

    private final List<IRule> RULE_LIST = new ArrayList<>(
            List.of(
                    new MakeCarRule(),
                    new ModelCarRule()
            )
    );

    @Override
    public List<Car> evaluate(Criteria criteria, List<Car> cars) {
        List<Car> filterredCars = new ArrayList<>(cars);
        for (IRule rule : RULE_LIST) {
            rule.validate(criteria, filterredCars);
        }
        return filterredCars;
    }
}
