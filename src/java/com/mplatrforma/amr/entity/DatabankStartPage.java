/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplatrforma.amr.entity;

import com.mresearch.databank.shared.PublicationDTO;
import com.mresearch.databank.shared.SocioResearchDTO_Light;
import com.mresearch.databank.shared.StartupBundleDTO;
import com.mresearch.databank.shared.ZaconDTO_Light;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author reshet
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "SP.getSingle", query = "SELECT x FROM DatabankStartPage x")
})
public class DatabankStartPage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
   
    
    @OneToMany(cascade= CascadeType.ALL)
    @OrderColumn
    private List<SocioResearch> res;
    @OneToMany(cascade= CascadeType.ALL)
    @OrderColumn
    private List<Zacon> laws;
    
    private Long pubs_last_show;
   
    //private MetaUnitMultivalued<MetaEntityType> root;
    
    public DatabankStartPage() {
        this.res = new ArrayList<SocioResearch>();
        this.laws = new ArrayList<Zacon>();
    }
     public static DatabankStartPage getStartPageSingleton(EntityManager em)
        {
            TypedQuery<DatabankStartPage> q = em.createNamedQuery("SP.getSingle", DatabankStartPage.class);
            DatabankStartPage d =  q.getSingleResult();
            return d;
        }
    public void updateFromDTO(EntityManager em,StartupBundleDTO rDTO)
	{
              setPubs_last_show(rDTO.getPubs_last_count());
              for(Zacon l:laws)
              {
                  if(!rDTO.getImportant_laws().contains(l.toDTO_Light()))laws.remove(l);
              }
              for(SocioResearch r:res)
              {
                  if(!rDTO.getTop_researchs().contains(r.toLightDTO()))res.remove(r);
              }
              for(ZaconDTO_Light dto:rDTO.getImportant_laws())
              {
                  Zacon z = em.find(Zacon.class, dto.getID());
                  laws.add(z);
              }
              for(SocioResearchDTO_Light dto:rDTO.getTop_researchs())
              {
                  SocioResearch s = em.find(SocioResearch.class, dto.getID());
                  res.add(s);
              }
              
	}
	public StartupBundleDTO toDTO()
	{
            StartupBundleDTO dto = new StartupBundleDTO();
            ArrayList<SocioResearchDTO_Light> re = new ArrayList<SocioResearchDTO_Light>();
            ArrayList<ZaconDTO_Light> lo = new ArrayList<ZaconDTO_Light>();
            for(Zacon l:laws)
            {
                lo.add(l.toDTO_Light());
            }
            for(SocioResearch s:res)
            {
                re.add(s.toLightDTO());
            }
            dto.setImportant_laws(lo);
            dto.setTop_researchs(re);
            dto.setPubs_last_count(getPubs_last_show());
            return dto;
	}
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatabankStartPage)) {
            return false;
        }
        DatabankStartPage other = (DatabankStartPage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mplatrforma.amr.entity.DatabankStructure[ id=" + id + " ]";
    }

    /**
     * @return the pubs_last_show
     */
    public Long getPubs_last_show() {
        return pubs_last_show;
    }

    /**
     * @param pubs_last_show the pubs_last_show to set
     */
    public void setPubs_last_show(Long pubs_last_show) {
        this.pubs_last_show = pubs_last_show;
    }
}
