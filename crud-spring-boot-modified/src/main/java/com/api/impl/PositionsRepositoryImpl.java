package com.api.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.api.interf.PositionsCustomMethods;

public class PositionsRepositoryImpl implements PositionsCustomMethods{

    @PersistenceContext
    private EntityManager em;

}
