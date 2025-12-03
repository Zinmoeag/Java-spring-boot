package com.jobapi.jobapi.ulits;

public interface Mapper<A, B> {
    B mapTo (A a);

    A mapFrom (B b);
}
