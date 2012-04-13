package com.mplatrforma.amr.entity;


import java.util.ArrayList;
import java.util.Date;

import com.mresearch.databank.shared.ArticleDTO;
import com.mresearch.databank.shared.OrgDTO;
import com.mresearch.databank.shared.ZaconDTO;
import com.mresearch.databank.shared.ZaconDTO_Light;
import javax.persistence.*;

@Entity
public class Zacon {
	@Id
        @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String name;
 	private String number;
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
		ZaconDTO dto = new ZaconDTO(name,getContents());
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
		ZaconDTO_Light dto = new ZaconDTO_Light(name);
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
