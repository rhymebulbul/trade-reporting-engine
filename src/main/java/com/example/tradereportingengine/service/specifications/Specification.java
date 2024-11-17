package com.example.tradereportingengine.service.specifications;

public interface Specification<T> {
    boolean isSatisfiedBy(T t);
}