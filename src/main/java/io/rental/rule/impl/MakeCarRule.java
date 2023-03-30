package io.rental.rule.impl;

import io.rental.Car;
import io.rental.Criteria;
import io.rental.rule.IRule;

import java.util.List;

public class MakeCarRule implements IRule {
    @Override
    public void validate(Criteria criteria, List<Car> cars) {
        if (cars != null && cars.size() > 0) {
            cars.removeIf(car -> !car.getMake().equals(criteria.getMake()));
        }
    }
}
