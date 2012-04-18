package com.mplatrforma.amr.entity;

import com.mresearch.databank.shared.*;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "SocioResearch.getResearchesLight", query = "SELECT NEW com.mresearch.databank.shared.SocioResearchDTO_Light(x.id, x.name) FROM SocioResearch x ORDER BY x.id"),
    @NamedQuery(name = "SocioResearch.getResearchesLightIN", query = "SELECT NEW com.mresearch.databank.shared.SocioResearchDTO_Light(x.id, x.name) FROM SocioResearch x WHERE x.id IN :idlist ORDER BY x.id")
})
public class SocioResearch extends AbstractSearchable{

    
        @Transient
        private EntityManager em;
        
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	private long id_search_repres;
	//private short vars_tagcloud_created;
	private String name;
	//private String org_prompter;

    public SocioResearch() {
    }
	public Long getFile_accessor_id() {
		return file_accessor_id;
	}
	private Long spssFile_blobkey;
	private Long file_accessor_id;
	private ArrayList<Long> var_ids;
//	private ArrayList<String> case_ids;
//	private Long org_order_id;
//	private Long org_impl_id;
//	private String org_order_name;
//	private String org_impl_name;
	private long var_weight_id;
	private String var_weight_name;
	private int selection_size;
//	private String gen_geathering;
//	private String method;
//	private String selection_appr_rand;
//	private String selection_appr_complexity;
//	private ArrayList<String> publications;
//	private ArrayList<String> publications_dois;
//	private ArrayList<String> publications_urls;
//	private ArrayList<String> researchers;
//	private ArrayList<String> concepts;
        public static String SEL_SIZE_NAME = "socioresearch_sel_size";
        public static String _NAME = "socioresearch_name";
        private String json_desctiptor;
        
        @OneToOne(cascade= CascadeType.PERSIST)
        private MetaUnitEntityItem entity_item;
	
	public String getJson_desctiptor() {
		return json_desctiptor;
	}


	public void setJson_desctiptor(String json_desctiptor) {
		this.json_desctiptor = json_desctiptor;
	}

        
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date start_date;
        
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date end_date;
	
        private ArrayList<String> files_ids;
	
        public ArrayList<String> getFiles_ids() {
		return files_ids;
	}
	public ArrayList<String> getFiles_descs() {
		return files_descs;
	}

	private ArrayList<String> files_descs;
	
	public long getID(){return id;}
	public void setSpssFile(long spssFile) {
		this.spssFile_blobkey = spssFile;
	}
	public long getSpssFile() {
		return spssFile_blobkey;
	}
	public void generateDDI3()
	{
		//TODO 
	}
	

	
	//public double getWeightedSize()
	public SocioResearch(SocioResearchDTO rDTO,EntityManager em)
	{
                this.em = em;
		//createFileAccessor();
		//vars_tagcloud_created = 0;
		this.setId_search_repres(createSearchRepresenter());
		this.setName(rDTO.getName());
		this.setSelection_size(rDTO.getSelection_size());
		this.setVar_ids(rDTO.getVar_ids());
		this.setVar_weight_id(rDTO.getVar_weight_id());
		this.setVar_weight_name(rDTO.getVar_weight_name());
	        this.entity_item = new MetaUnitEntityItem(rDTO.getName());
                this.entity_item.setMapped_values(rDTO.getFilling());
               // this.entity_item.getMapped_values().put(SocioResearch.SEL_SIZE_NAME, String.valueof(rDTO.se));
		//updateEntityRepresent(id_search_repres, name,em);
		//if (vars_tagcloud_created==0)generateVarsTagCloud();
		//this.org_prompter = rDTO.getOrg_prompter();
	}
	private void createFileAccessor()
	{
		ResearchFilesAccessor accessor;
		try{
			accessor = new ResearchFilesAccessor();
			em.persist(accessor);
			this.file_accessor_id = accessor.getId();
		}finally
		{
			//em.close();
		}
	}
	public void updateFileAccessor(EntityManager em,ResearchFilesDTO dto)
	{
		ResearchFilesAccessor accessor;
		try{
			accessor = em.find(ResearchFilesAccessor.class,file_accessor_id);
			accessor.updateFromDTO(dto);
		}finally
		{
			//em.close();
		}
	}
	private void generateVarsTagCloud()
	{
	    try {
	    	for(Long var_id:var_ids)
			{
	    		Var dsVar, detached;
	    		dsVar = em.find(Var.class, var_id);
	   	       // detached = pm.detachCopy(dsVar);
	   	      //  VarDTO_Light dto = new VarDTO_Light();
	   	        
	   	        VarDTO dto = dsVar.toDTO();
	   	        updateTagCloudBridge("var-"+String.valueOf(var_id), getPermutations(dto.getLabel()), id_search_repres,em);
	   	        int i = 0;
	   	        
//	   	        for(String alt_name:dto.getV_label_values())
//	   	        {
//	   	        	updateTagCloudBridge(var_id+"-"+String.valueOf(i), getPermutations(alt_name), id_search_repres);
//	   	        	i++;
//		   	    }
			}  
	    //	vars_tagcloud_created = 1;
	    } finally {
	     // em.close();
	    }
	}
	public SocioResearch(EntityManager em)
	{
               // em = new RxStorageSessionBean().getEM();
                this.em = em;
                this.entity_item = new MetaUnitEntityItem();
           //     vars_tagcloud_created = 0;
		createFileAccessor();
	//this.setId_search_repres(createSearchRepresenter());
	}
        
         public static ArrayList<SocioResearchDTO_Light> getResearchsLight(EntityManager em)
        {
           
            TypedQuery<SocioResearchDTO_Light> q = em.createNamedQuery("SocioResearch.getResearchesLight", SocioResearchDTO_Light.class );
            List<SocioResearchDTO_Light> l = q.getResultList();
            return new ArrayList<SocioResearchDTO_Light>(l);
        }
        public static ArrayList<SocioResearchDTO_Light> getResearchsLightDTOs(EntityManager em, ArrayList<Long> ids)
        {
           
            TypedQuery<SocioResearchDTO_Light> q = em.createNamedQuery("SocioResearch.getResearchesLightIN", SocioResearchDTO_Light.class );
            q.setParameter("idlist", ids);
            List<SocioResearchDTO_Light> l = q.getResultList();
            return new ArrayList<SocioResearchDTO_Light>(l);
        }
	public SocioResearchDTO toDTO()
	{
		SocioResearchDTO  dto = new SocioResearchDTO();
		dto.setId(id);
		dto.setName(getName());
		dto.setSelection_size(getSelection_size());
		//dto.setSelection_appr(getSelection_appr());
		dto.setVar_ids(getVar_ids());
		//this.setPublications_urls(rDTO.getPublications_urls());
		
		dto.setVar_weight_id(getVar_weight_id());
		dto.setVar_weight_name(getVar_weight_name());
		dto.setFile_accessor_id(this.file_accessor_id);
                if(this.entity_item!=null && this.entity_item.getMapped_values()!=null)
                        dto.setFilling(this.entity_item.getMapped_values());
                
		return dto;
	}
        public SocioResearchDTO toLightDTO()
	{
		SocioResearchDTO  dto = new SocioResearchDTO();
		dto.setId(id);
		dto.setName(getName());
		return dto;
	}
      
	public ResearchFilesDTO toFilesDTO(EntityManager em)
	{
	    try {
	    		ResearchFilesAccessor accessor,detached;
	    		accessor = em.find(ResearchFilesAccessor.class,file_accessor_id);
	    		//detached = em.detach(accessor);
	    		return accessor.toDTO();
	    } finally {
	     // em.close();
	    }
	}
	public void updateFromDTO(SocioResearchDTO rDTO,EntityManager em)
	{
                this.em = em;
		if (id_search_repres == 0) id_search_repres = createSearchRepresenter();
		if (getSearchRepresenter(id_search_repres,em).getEntity_id() == 0) updateEntityID(id, id_search_repres,em);
		this.setName(rDTO.getName());
		this.setStart_date(rDTO.getStart_date());
		this.setEnd_date(rDTO.getEnd_date());
		this.setSelection_size(rDTO.getSelection_size());
		//this.var_ids = rDTO.getVar_ids();
		
		this.setVar_weight_id(rDTO.getVar_weight_id());
		this.setVar_weight_name(rDTO.getVar_weight_name());
		if(this.entity_item == null) this.entity_item = new MetaUnitEntityItem(rDTO.getName());
                this.entity_item.setMapped_values(rDTO.getFilling());
		//updateEntityRepresent(id_search_repres, name,em);
		//if (vars_tagcloud_created==0)generateVarsTagCloud();
		//this.org_prompter =rDTO.getOrg_prompter();
	}
	
	public void updateFromDTOGrouped(SocioResearchDTO rDTO,EntityManager em)
	{
                this.em= em;
		if (id_search_repres == 0) id_search_repres = createSearchRepresenter();
		if (getSearchRepresenter(id_search_repres,em).getEntity_id() == 0) updateEntityID(id, id_search_repres,em);
		//this.setName(rDTO.getName());
		this.setStart_date(rDTO.getStart_date());
		this.setEnd_date(rDTO.getEnd_date());
		this.setSelection_size(rDTO.getSelection_size());
		//this.var_ids = rDTO.getVar_ids();
		
		
		//this.setVar_weight_id(rDTO.getVar_weight_id());
		//this.setVar_weight_name(rDTO.getVar_weight_name());
		if(this.entity_item == null) this.entity_item = new MetaUnitEntityItem(rDTO.getName());
                this.entity_item.setMapped_values(rDTO.getFilling());
		//updateEntityRepresent(id_search_repres, name);
		//if (vars_tagcloud_created==0)generateVarsTagCloud();
		//this.org_prompter =rDTO.getOrg_prompter();
	}
	
	@Override
	public long createSearchRepresenter() {
		EntitySearchRepresenter repres;
		try{
			repres = new EntitySearchRepresenter();
			repres.setEntityType(SocioResearch.class.getName());
//			repres.setText_represent(getName());
			//repres.setEntity_id();
                        
			em.persist(repres);
		}finally
		{
			//em.close();
		}
		return repres.getID();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		if (id_search_repres != 0){}
			//this.updateTagCloudBridge("name", getPermutations(this.name), getId_search_repres(),em);
	}
	
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public int getSelection_size() {
		return selection_size;
	}
	
	public void setSelection_size(int selection_size) {
		this.selection_size = selection_size;
	}
	
	public ArrayList<Long> getVar_ids() {
		return var_ids;
	}
	public void setVar_ids(ArrayList<Long> var_ids) {
		this.var_ids = var_ids;
	}
	
	public long getVar_weight_id() {
		return var_weight_id;
	}
	public void setVar_weight_id(long var_weight_id) {
		this.var_weight_id = var_weight_id;
	}
	public String getVar_weight_name() {
		return var_weight_name;
	}
	public void setVar_weight_name(String var_weight_name) {
		this.var_weight_name = var_weight_name;
	}
	
	public long getId_search_repres() {
		return id_search_repres;
	}
	public void setId_search_repres(long id_search_repres) {
		this.id_search_repres = id_search_repres;
	}
	

	public void addFile(String id,String desc)
	{
		if(files_ids == null) files_ids = new ArrayList<String>();
		if(files_descs == null) files_descs = new ArrayList<String>();
		
		files_ids.add(id);
		files_descs.add(desc);
	}
	

    /**
     * @return the entity_item
     */
    public MetaUnitEntityItem getEntity_item() {
        return entity_item;
    }

    /**
     * @param entity_item the entity_item to set
     */
    public void setEntity_item(MetaUnitEntityItem entity_item) {
        this.entity_item = entity_item;
    }
}
