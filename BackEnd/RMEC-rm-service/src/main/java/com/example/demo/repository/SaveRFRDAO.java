
package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Rfr_New;


@Repository
public class SaveRFRDAO {
	@PersistenceContext
    EntityManager em;
	
//   @Transactional(readOnly=false)
//	    public long insertRFR(Rfr rfr) {
//		   	
//		 
////		   em.setFlushMode(FlushModeType.COMMIT);
//	         em.persist(rfr);
////	   
//	         em.flush();
////	         
////	       //  em.getTransaction().commit(); 
////	         em.remove(rfr);
//	         
//	        
//	         
//	         
//	         return rfr.getRfrId(); // ???
//	    }
   
   @Transactional(readOnly=false)
   public long insertRFR_new(Rfr_New rfr) {
	   	
	 
//	   em.setFlushMode(FlushModeType.COMMIT);
        em.persist(rfr);
//  
        em.flush();
//        
//      //  em.getTransaction().commit(); 
//        em.remove(rfr);
        
       
        
        
        return rfr.getRfrId(); // ???
   }
}

