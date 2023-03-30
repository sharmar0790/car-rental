package io.rental;

import io.rental.rule.IRuleEvaluator;
import io.rental.rule.impl.RuleEvaluator;
import io.utils.DatePeriod;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Criteria {
    private final String model;
    private final String make;
    private final DatePeriod datePeriod;

    private final IRuleEvaluator ruleEvaluator = new RuleEvaluator();

    public Criteria(@NotNull String model, @NotNull String make, DatePeriod datePeriod) {
        this.model = model;
        this.make = make;
        this.datePeriod = datePeriod;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public DatePeriod getDatePeriod() {
        return datePeriod;
    }

    public boolean findMatchingCar(Car car) {
        if (StringUtils.isNotEmpty(this.make) && StringUtils.isNotEmpty(this.model)) {
            return car.getMake().equals(this.make) && car.getModel().equals(this.model);
        } else if (StringUtils.isNotEmpty(this.make) && StringUtils.isEmpty(this.model)) {
            return car.getMake().equals(this.make);
        } else {
            return car.getMake().equals(this.model);
        }
    }

    public List<Car> findMatchingCar(List<Car> cars) {
        return ruleEvaluator.evaluate(this, cars);
    }
}
