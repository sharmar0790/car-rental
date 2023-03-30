package io.rental.rule;

import io.rental.Car;
import io.rental.Criteria;

import java.util.List;

public interface IRule {

    void validate(Criteria criteria, List<Car> cars);
}
