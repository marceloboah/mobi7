package com.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.api.domain.Pois;
import com.api.interf.PoisCustomMethods;

@Repository
public class PoisRepositoryImpl implements PoisCustomMethods{

    @PersistenceContext
    private EntityManager em;

	
	@Override
    public Pois getPoisBySearch(String name, Double valmin, Double valmax, Integer page) {
		Pois retorno = new Pois();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pois> criteriaQuery = cb.createQuery(Pois.class);
        Root<Pois> ptable = criteriaQuery.from(Pois.class);
        
        criteriaQuery.select(ptable);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (name !=  null && !name.equals("null")) {
            predicates.add(cb.like(ptable.get("product"), name));
            if(valmin!= null &&valmax != null) {
            	if (valmin > -1 && valmax > -1) {
                    
                    predicates.add(cb.between(ptable.get("price"), valmin, valmax));
                }
            }
            
            
            criteriaQuery.select(ptable).where(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.orderBy(cb.asc(ptable.get("product")));

            TypedQuery<Pois> query = em.createQuery(criteriaQuery);

            
            
            List<Pois> list = query.getResultList();
            
            int totalRows = query.getResultList().size();
            retorno.setPageTotalLines(totalRows);
            retorno.setPageNumber(page);
            query.setFirstResult(page * 50);
            query.setMaxResults(50);
            
            //List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
            retorno.getListPois().addAll(list);
            return retorno;
        }else {
        	if(valmin!= null &&valmax != null) {
        		if (valmin > -1 && valmax > -1) {
                    
                	criteriaQuery.select(ptable).where(cb.between(ptable.get("price"), valmin, valmax));
                	criteriaQuery.orderBy(cb.asc(ptable.get("product")));
                }
        	}
        	
            
        	TypedQuery<Pois> query = em.createQuery(criteriaQuery);

            int totalRows = query.getResultList().size();
            retorno.setPageTotalLines(totalRows);
            retorno.setPageNumber(page);
            query.setFirstResult(page * 50);
            query.setMaxResults(50);
            
            List<Pois> list = query.getResultList();
            retorno.getListPois().addAll(list);
            return retorno;
        	
        }
        
    }


	@Override
	public Pois getAllPoisByPage(Integer page) {
		Pois retorno = new Pois();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pois> criteriaQuery = cb.createQuery(Pois.class);
        Root<Pois> ptable = criteriaQuery.from(Pois.class);
        
        criteriaQuery.select(ptable);
        
        criteriaQuery.select(ptable);
    	criteriaQuery.orderBy(cb.asc(ptable.get("product")));
    	TypedQuery<Pois> query = em.createQuery(criteriaQuery);

        int totalRows = query.getResultList().size();
        retorno.setPageTotalLines(totalRows);
        retorno.setPageNumber(page);
        query.setFirstResult(page * 50);
        query.setMaxResults(50);
        
        List<Pois> list = query.getResultList();
        retorno.getListPois().addAll(list);
        return retorno;
	}


	@Override
	public List<Pois> getPoisByList(String name, Double valmin, Double valmax, Integer page) {
		Pois retorno = new Pois();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pois> criteriaQuery = cb.createQuery(Pois.class);
        Root<Pois> ptable = criteriaQuery.from(Pois.class);
        
        criteriaQuery.select(ptable);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (name !=  null && !name.equals("null")) {
            predicates.add(cb.like(ptable.get("product"), name));
            if (valmin > -1 && valmax > -1) {
                
                predicates.add(cb.between(ptable.get("price"), valmin, valmax));
            }
            
            criteriaQuery.select(ptable).where(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.orderBy(cb.asc(ptable.get("product")));


            
            //List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
            TypedQuery<Pois> query = em.createQuery(criteriaQuery);

            int totalRows = query.getResultList().size();
            retorno.setPageTotalLines(totalRows);
            retorno.setPageNumber(page);
            query.setFirstResult(page * 50);
            query.setMaxResults(50);
            
            List<Pois> list = query.getResultList();
            
            //List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
            
            return list;
        }else {
        	
        	if (valmin > -1 && valmax > -1) {
                
            	criteriaQuery.select(ptable).where(cb.between(ptable.get("price"), valmin, valmax));
            	criteriaQuery.orderBy(cb.asc(ptable.get("product")));
            }
         
        	//List<Product> list = em.createQuery(criteriaQuery).getResultList();
            
        	TypedQuery<Pois> query = em.createQuery(criteriaQuery);

            int totalRows = query.getResultList().size();
            retorno.setPageTotalLines(totalRows);
            retorno.setPageNumber(page);
            query.setFirstResult(page * 50);
            query.setMaxResults(50);
            
            List<Pois> list = query.getResultList();
            
            return list;
        	
        }
	}

}