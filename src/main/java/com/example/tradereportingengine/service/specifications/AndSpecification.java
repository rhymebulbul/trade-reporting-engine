package com.example.tradereportingengine.service.specifications;

public class AndSpecification<T> implements Specification<T> {
    private final Specification<T> spec1;
    private final Specification<T> spec2;

    public AndSpecification(Specification<T> spec1, Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t);
    }
}