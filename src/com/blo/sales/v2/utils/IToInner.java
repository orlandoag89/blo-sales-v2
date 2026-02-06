package com.blo.sales.v2.utils;

public interface IToInner<I, O> {
    
    I toInner(O outer);
    
}
