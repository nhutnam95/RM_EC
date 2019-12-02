
package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Rap;
import com.example.demo.model.Rfr_New;


@Repository
public class SaveDAO {
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
   @Transactional(readOnly=false)
   public long insertRAP_new(Rap rap) {
	   	
        em.persist(rap);  
        em.flush();     
      return rap.getRapId(); // ???
   }
}

