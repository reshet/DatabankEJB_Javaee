package com.mplatrforma.amr.entity;


import java.util.ArrayList;
import java.util.Date;

import com.mresearch.databank.shared.ArticleDTO;
import com.mresearch.databank.shared.OrgDTO;
import com.mresearch.databank.shared.ZaconDTO;
import com.mresearch.databank.shared.ZaconDTO_Light;
import java.util.List;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Zacon.getAllLight", query = "SELECT NEW com.mresearch.databank.shared.ZaconDTO_Light(x.id, x.name) FROM Zacon x ORDER BY x.id"),
    @NamedQuery(name = "Zacon.getLightIN", query = "SELECT NEW com.mresearch.databank.shared.ZaconDTO_Light(x.id, x.name) FROM Zacon x WHERE x.id IN :idlist ORDER BY x.id"),
    @NamedQuery(name = "Zacon.getFullIN", query = "SELECT NEW com.mresearch.databank.shared.ZaconDTO(x.id, x.name,x.contents,x.enclosure_key,y.mapped_values) "
                                                 + "FROM Zacon x INNER JOIN x.entity_item y"
                                                    + " WHERE x.id IN :idlist ORDER BY x.id")

})
public class Zacon {
	@Id
        @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String name;
 	private String number;
        
        @Column(columnDefinition="TEXT")
	private String contents;
	private Long enclosure_key;
       
        @OneToOne(cascade= CascadeType.PERSIST)
        private MetaUnitEntityItem entity_item;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Zacon()
	{
             this.entity_item = new MetaUnitEntityItem("zacon");
        }
	public Zacon(ZaconDTO dto)
	{
                this.entity_item = new MetaUnitEntityItem(dto.getHeader());
                this.entity_item.setMapped_values(dto.getFilling());
                this.name = dto.getHeader();
		this.setContents(dto.getContents());
		this.setEnclosure_key(dto.getEnclosure_key());
		//this.setDate(dto.getDate());
		//this.setAccept_date(dto.getAccept_date());
		//this.setDecline_date(dto.getDecline_date());
		//this.setKey_words(dto.getKey_words());
		this.setNumber(dto.getNumber());
		//this.setAuthors(dto.getAuthors());
	}	
        
         public static ArrayList<ZaconDTO_Light> getAllZaconLightDTOs(EntityManager em)
        {
            TypedQuery<ZaconDTO_Light> q = em.createNamedQuery("Zacon.getAllLight", ZaconDTO_Light.class);
            List<ZaconDTO_Light> l = q.getResultList();
            return new ArrayList<ZaconDTO_Light>(l);
        }
        public static ArrayList<ZaconDTO_Light> getZaconLightDTOs(EntityManager em, ArrayList<Long> ids)
        {
            TypedQuery<ZaconDTO_Light> q = em.createNamedQuery("Zacon.getLightIN", ZaconDTO_Light.class );
            q.setParameter("idlist", ids);
            List<ZaconDTO_Light> l = q.getResultList();
            return new ArrayList<ZaconDTO_Light>(l);
        }
        public static ArrayList<ZaconDTO> getZaconFullDTOs(EntityManager em, ArrayList<Long> ids)
        {
            TypedQuery<ZaconDTO> q = em.createNamedQuery("Zacon.getFullIN", ZaconDTO.class);
            q.setParameter("idlist", ids);
            List<ZaconDTO> l = q.getResultList();
            return new ArrayList<ZaconDTO>(l);
        }
        public void updateFromDTO(ZaconDTO rDTO)
	{
                if(this.entity_item == null) this.entity_item = new MetaUnitEntityItem(rDTO.getHeader());
                this.entity_item.setMapped_values(rDTO.getFilling());
                this.name = rDTO.getHeader();
                this.contents = rDTO.getContents();
                this.number = rDTO.getNumber();
                this.enclosure_key = rDTO.getEnclosure_key();
	}
	public ZaconDTO toDTO()
	{
		ZaconDTO dto = new ZaconDTO(id,name,getContents());
		dto.setId(this.getId());
		dto.setEnclosure_key(getEnclosure_key());
		//dto.setDate(date);
		//dto.setAccept_date(accept_date);
		//dto.setDecline_date(decline_date);
		//dto.setKey_words(key_words);
		dto.setNumber(number);
                dto.setFilling(entity_item.getMapped_values());
		//dto.setAuthors(authors);
		return dto;
	}
        public ZaconDTO_Light toDTO_Light()
	{
		ZaconDTO_Light dto = new ZaconDTO_Light(id,name);
		dto.setId(this.getId());
		//dto.setEnclosure_key(getEnclosure_key());
		//dto.setDate(date);
		//dto.setAccept_date(accept_date);
		//dto.setDecline_date(decline_date);
		//dto.setKey_words(key_words);
		dto.setNumber(number);
                //dto.setFilling(entity_item.getMapped_values());
		//dto.setAuthors(authors);
		return dto;
	}
	public Long getId() {
		return id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Long getEnclosure_key() {
		return enclosure_key;
	}
	public void setEnclosure_key(Long enclosure_key) {
		this.enclosure_key = enclosure_key;
	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	public Date getAccept_date() {
//		return accept_date;
//	}
//	public void setAccept_date(Date accept_date) {
//		this.accept_date = accept_date;
//	}
//	public Date getDecline_date() {
//		return decline_date;
//	}
//	public void setDecline_date(Date decline_date) {
//		this.decline_date = decline_date;
//	}
//	public ArrayList<String> getKey_words() {
//		return key_words;
//	}
//	public void setKey_words(ArrayList<String> key_words) {
//		this.key_words = key_words;
//	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
//	public ArrayList<String> getAuthors() {
//		return authors;
//	}
//	public void setAuthors(ArrayList<String> authors) {
//		this.authors = authors;
//	}
}
