package io.rental.rule;

import io.rental.Car;
import io.rental.Criteria;

import java.util.List;

public interface IRuleEvaluator {

    List<Car> evaluate(Criteria criteria, List<Car> cars);
}
