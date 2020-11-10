package com.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.api.domain.Metrics;
import com.api.domain.Pois;
import com.api.interf.MetricsCustomMethods;


@Repository
public class MetricsRepositoryImpl implements MetricsCustomMethods{

    @PersistenceContext
    private EntityManager em;

	@Override
	public Metrics getAllMetricsByPage(Integer page) {
		Metrics retorno = new Metrics();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Metrics> criteriaQuery = cb.createQuery(Metrics.class);
        Root<Metrics> ptable = criteriaQuery.from(Metrics.class);
        
        criteriaQuery.select(ptable);
        
    	criteriaQuery.orderBy(cb.asc(ptable.get("id")));
    	TypedQuery<Metrics> query = em.createQuery(criteriaQuery);

        int totalRows = query.getResultList().size();
        retorno.setPageTotalLines(totalRows);
        retorno.setPageNumber(page);
        query.setFirstResult(page * 50 - 50);
        query.setMaxResults(50);
        
        List<Metrics> list = query.getResultList();
        retorno.getListMetrics().addAll(list);
        return retorno;
	}

	@Override
	public Metrics getMetricsBySearch(String name, Double valmin, Double valmax, Integer page) {
		
		Metrics retorno = new Metrics();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Metrics> criteriaQuery = cb.createQuery(Metrics.class);
        Root<Metrics> ptable = criteriaQuery.from(Metrics.class);
        
        criteriaQuery.select(ptable);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (name !=  null && !name.equals("null")) {
            predicates.add(cb.like(ptable.get("metrics"), name));
            if(valmin!= null && valmax != null) {
            	if (valmin > -1 && valmax > -1) {
                    
                    predicates.add(cb.between(ptable.get("placa"), valmin, valmax));
                }
            }
            
            
            criteriaQuery.select(ptable).where(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.orderBy(cb.asc(ptable.get("id")));

            TypedQuery<Metrics> query = em.createQuery(criteriaQuery);

            
            
            List<Metrics> list = query.getResultList();
            
            int totalRows = query.getResultList().size();
            retorno.setPageTotalLines(totalRows);
            retorno.setPageNumber(page);
            query.setFirstResult(page * 50 - 50);
            query.setMaxResults(50);
            
            //List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
            retorno.getListMetrics().addAll(list);
            return retorno;
        }else {
        	if(valmin!= null &&valmax != null) {
        		if (valmin > -1 && valmax > -1) {
                    
                	criteriaQuery.select(ptable).where(cb.between(ptable.get("data"), valmin, valmax));
                	criteriaQuery.orderBy(cb.asc(ptable.get("id")));
                }
        	}
        	
            
        	TypedQuery<Metrics> query = em.createQuery(criteriaQuery);

        	
            int totalRows = query.getResultList().size();
            retorno.setPageTotalLines(totalRows);
            retorno.setPageNumber(page);
            query.setFirstResult(page * 50 - 50);
            query.setMaxResults(50);
            
            List<Metrics> list = query.getResultList();
            retorno.getListMetrics().addAll(list);
            return retorno;
        	
        }
	}

	@Override
	public Metrics getMetricByList(String placa, Date valmin, Date valmax, Integer page) {
		
		List<Metrics> metricsList = new ArrayList<Metrics>();
		
		Metrics retorno = new Metrics();
		
		StringBuilder sb = new StringBuilder();
        sb.append("SELECT metrics FROM Metrics metrics WHERE 1=1 ");

        if(placa !=null && !placa.equals("null")) {
            sb.append(" AND placa =  :placa ");
        }
        
        if(valmin !=null && !valmin.equals("null")) {
            sb.append(" AND metrics.dataPosicao >= :valmin ");
        }
        
        if(valmax !=null && !valmax.equals("null")) {
            sb.append(" AND metrics.dataPosicao <= :valmax ");
        }
        
        sb.append(" ORDER BY metrics.dataPosicao  ");
        
        TypedQuery<Metrics> query = em.createQuery(sb.toString(), Metrics.class);
        
        
        if(placa !=null && !placa.equals("null")) {
            query.setParameter("placa", placa);
        }
        
        if(valmin !=null && !valmin.equals("null")) {
            query.setParameter("valmin", valmin);
        }
        
        if(valmax !=null && !valmax.equals("null")) {
            query.setParameter("valmax", valmax);
        }
        
        
        
        
        int totalRows = query.getResultList().size();
        retorno.setPageTotalLines(totalRows);
        retorno.setPageNumber(page);
        
        query.setFirstResult(page * 50 - 50);
        query.setMaxResults(50);
        
        metricsList = query.getResultList();
        retorno.getListMetrics().addAll(metricsList);
        return retorno;
        
        		
	}

	

}
