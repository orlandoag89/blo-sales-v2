package com.blo.sales.v2.utils;

public interface IToOuter<I, O> {
    
    O toOuter(I inner);
    
}
