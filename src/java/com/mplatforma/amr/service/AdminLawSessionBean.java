/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplatforma.amr.service;

import com.mplatforma.amr.service.remote.AdminLawBeanRemote;
import com.mplatforma.amr.service.remote.RxStorageBeanRemote;
import com.mplatrforma.amr.entity.Article;
import com.mplatrforma.amr.entity.SocioResearch;
import com.mplatrforma.amr.entity.Var;
import com.mplatrforma.amr.entity.Zacon;
import com.mresearch.databank.jobs.IndexLawJobFast;
import com.mresearch.databank.shared.ArticleDTO;
import com.mresearch.databank.shared.ZaconDTO;
import com.mresearch.databank.shared.ZaconDTO_Light;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jms.*;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author reshet
 */
@WebService
@Stateless(mappedName="AdminLawRemoteBean",name="AdminLawRemoteBean")
public class AdminLawSessionBean implements AdminLawBeanRemote{

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private RxStorageBeanRemote store;

    @Override
    public Boolean deleteArticle(Long id) {
        try {
            Article article = em.find(Article.class, id);
            if (article != null) {
                em.remove(article);
            }
            } finally {
            }
            return true;
    }

    @Override
    public ArticleDTO getArticle(Long id) {
        Article dsArticle, detached;
         try {
        dsArticle = em.find(Article.class, id);
        } finally {
        }
        return dsArticle.toDTO();
    }

    @Override
    public ArticleDTO updateArticle(ArticleDTO aDTO) {
            if (aDTO.getId() == 0){ // create new
	      Article newArticle = addArticle(aDTO);
	      return newArticle.toDTO();
	    }

	    Article article = null;
	    try {
	      article = em.find(Article.class, aDTO.getId());
	      article.setName(aDTO.getHeader());
	      article.setContents(aDTO.getContents());
	      article.setEnclosure_key(aDTO.getEnclosure_key());
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	    }
	    return aDTO;
    }

    @Override
    public ArrayList<ArticleDTO> getArticleDTOs(ArrayList<Long> keys) {
	ArrayList<ArticleDTO> arr = new ArrayList<ArticleDTO>();
	if (keys != null)
            for(Long key:keys)
            {
                    ArticleDTO dto = this.getArticle(key);
                    arr.add(dto);
            }
	return arr;
    }

    @Override
    public ArrayList<ArticleDTO> getArticlesAll() {
	ArrayList<ArticleDTO> list = new ArrayList<ArticleDTO>();
	try {
            TypedQuery<Article> q = em.createQuery("select x from Article x",Article.class);
            List<Article> res = (List<Article>)q.getResultList();
		for(Article art:res)
		{
			list.add(art.toDTO());
		}
            } finally {
        }
	return list;

    }

    @Override
    public Boolean deleteZacon(Long id) {
	try {
        Zacon zacon = em.find(Zacon.class, id);
        if (zacon != null) {
            em.remove(zacon);
        }
        } finally {
        }
        return true;

    }

    @Override
    public ZaconDTO getZacon(Long id) {
        Zacon dsZacon, detached;

        try {
        dsZacon = em.find(Zacon.class, id);
        } finally {
        }

        return dsZacon.toDTO();

    }
    
    public ZaconDTO_Light getZaconLight(Long id) {
        Zacon dsZacon, detached;

        try {
        dsZacon = em.find(Zacon.class, id);
        } finally {
        }

        return dsZacon.toDTO_Light();

    }
        @Resource(mappedName="jms/myQCF")
    private  QueueConnectionFactory connectionFactory;

    @Resource(mappedName="jms/spss_parse")
    private  Queue queue;

    
     private QueueConnection connection;
    private QueueSession session;
    QueueSender q_sender;
    @PostConstruct
    private void init()
    {
     try {
             connection = connectionFactory.createQueueConnection();
             session = connection.createQueueSession(false, 0);
             q_sender = session.createSender(queue);

        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    @PreDestroy
    private void release()
    {
       try {
            q_sender.close();
            connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
       private void launchIndexingLaw(ZaconDTO dto)
    {
         try {
            ObjectMessage message = session.createObjectMessage();
            message.setStringProperty("title", "command to index LAW");
            // here we create NewsEntity, that will be sent in JMS message
           // ParseSpssJob job = new ParseSpssJob(blobkey, length);
            IndexLawJobFast job = new IndexLawJobFast(dto);
            message.setObject(job);    
           // message.setJMSDestination(queue);
            q_sender.send(message);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public ZaconDTO updateZacon(ZaconDTO aDTO) {
	if (aDTO.getId() == 0){ // create new
	      Zacon newZacon = addZacon(aDTO);
	      return newZacon.toDTO();
	    }

	    Zacon Zacon = null;
	    try {
	      Zacon = em.find(Zacon.class, aDTO.getId());
	      Zacon.setName(aDTO.getHeader());
	      Zacon.setContents(aDTO.getContents());
	      Zacon.setEnclosure_key(aDTO.getEnclosure_key());
              
              em.persist(Zacon);
              launchIndexingLaw(aDTO);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	    }
	    return aDTO;
    }

    @Override
    public ArrayList<ZaconDTO_Light> getZaconDTOs(ArrayList<Long> keys) {
	ArrayList<ZaconDTO_Light> arr = new ArrayList<ZaconDTO_Light>();
	if (keys != null)
            for(Long key:keys)
            {
                ZaconDTO_Light dto = this.getZaconLight(key);
                arr.add(dto);
            }
	return arr;
    }

    @Override
    public ArrayList<ZaconDTO_Light> getZaconsAll() {
	ArrayList<ZaconDTO_Light> list = new ArrayList<ZaconDTO_Light>();
   // SocioResearch dsResearch, detached;
	try {
            TypedQuery<Zacon> q = em.createQuery("select x from Zacon x",Zacon.class);
		List<Zacon> res = (List<Zacon>)q.getResultList();
		for(Zacon art:res)
		{
			list.add(art.toDTO_Light());
		}
            } finally {
        }
	return list;

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

     private Article addArticle(ArticleDTO articleDTO) {
      Article article = null;
      try {
      // for this version of the app, just get hardwired 'default' user
      //UserAccount currentUser = UserAccount.getDefaultUser(); // detached object
      //currentUser = pm.makePersistent(currentUser); // attach
        article = new Article(articleDTO);
        em.persist(article);
      } finally {
        em.close();
      }
      return article;
}
private Zacon addZacon(ZaconDTO ZaconDTO) {
    Zacon Zacon = null;
    try {
    // for this version of the app, just get hardwired 'default' user
    //UserAccount currentUser = UserAccount.getDefaultUser(); // detached object
    //currentUser = pm.makePersistent(currentUser); // attach
      Zacon = new Zacon(ZaconDTO);
      em.persist(Zacon);
      launchIndexingLaw(ZaconDTO);
    } finally {
    }
    return Zacon;
}

    @Override
    public ArrayList<ZaconDTO> getZaconDTOs_Normal(ArrayList<Long> keys) {
        ArrayList<ZaconDTO> arr = new ArrayList<ZaconDTO>();
	if (keys != null)
            for(Long key:keys)
            {
                ZaconDTO dto = this.getZacon(key);
                arr.add(dto);
            }
	return arr;
    }

}
