package com.mplatforma.amr.service.remote;

import com.mresearch.databank.shared.*;
import javax.ejb.Remote;

import java.util.ArrayList;
import java.util.HashMap;
import javax.persistence.EntityManager;

@Remote
public interface AdminSocioResearchBeanRemote {
      Boolean deleteResearch(long id);
      SocioResearchDTO updateResearch(SocioResearchDTO research);
      void updateVar(VarDTO_Detailed var);
      SocioResearchDTO updateResearchGrouped(SocioResearchDTO research);

      VarDTO_Detailed generalizeVar(long var_id, ArrayList<Long> gen_var_ids,UserAccountDTO dto);

      long parseSPSS(long blobkey, long length);

      long addOrgImpl(OrgDTO dto);

      Boolean addFileToAccessor(long id_research,long id_file,String desc,String category);
      Boolean deleteFileFromAccessor(long id_research,long id_file);
      Boolean addSSE(String clas,String kind,String value);
      Boolean updateFileAccessor(long research_id,ResearchFilesDTO dto);
      Boolean addMetaUnit(long parent_id,MetaUnitDTO dto);
      void updateMetaUnitStructure(MetaUnitDTO dto);
      void addEntityItem(Long entity_id,String value,HashMap<String,String> filling);
      void addSubEntityItem(Long parent_id,String value,HashMap<String,String> filling);
      void editEntityItem(Long id,String value,HashMap<String,String> filling);
      void deleteEntityItem(Long id,Long entity_id);
      void deleteMetaUnit(Long id,Long unit_parent_id);
      void updateMetaUnitEntityItemLinks(MetaUnitEntityItemDTO old,MetaUnitEntityItemDTO nev);
      void updateMetaUnitEntityItemLinks(MetaUnitEntityItemDTO dto);
      void updateMetaUnitEntityItemLinks(Long item_id,ArrayList<Long> tagged_ids,String identifier);
      
      void setStartupContent(StartupBundleDTO dto);
      void reIndexAllResearches(EntityManager em);
}
