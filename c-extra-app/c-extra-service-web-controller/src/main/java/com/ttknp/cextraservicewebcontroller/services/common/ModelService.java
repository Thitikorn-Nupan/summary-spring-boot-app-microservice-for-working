package com.ttknp.cextraservicewebcontroller.services.common;

import java.util.List;

public abstract class ModelService <T> {
    public abstract List<T> retrieveAll() ;
    public abstract <U> T retrieveBy(U key) ;
    public abstract <U,E> E retrieveColumnBy(U key) ;
}
