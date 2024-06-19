package com.develhope.spring.vehicles.components.specifications;

import com.develhope.spring.vehicles.models.Vehicle;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class VehicleSpecificationsBuilder {

    private final List<SpecSearchCriteria> params;

    public VehicleSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public final VehicleSpecificationsBuilder with(String key, String operation, Object value,
                                                   String prefix, String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final VehicleSpecificationsBuilder with(String orPredicate, String key, String operation,
                                                   Object value, String prefix, String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix != null &&
                prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                boolean endWithAsterisk = suffix != null &&
                        suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<Vehicle> build() {
        if (params.isEmpty())
            return null;

        Specification<Vehicle> result = new VehicleSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new VehicleSpecification(params.get(i)))
                    : Specification.where(result).and(new VehicleSpecification(params.get(i)));
        }

        return result;
    }

    public final VehicleSpecificationsBuilder with(VehicleSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final VehicleSpecificationsBuilder with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}