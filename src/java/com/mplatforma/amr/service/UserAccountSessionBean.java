/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplatforma.amr.service;

import com.mplatforma.amr.service.remote.AdminPubBeanRemote;
import com.mplatforma.amr.service.remote.UserAccountBeanRemote;
import com.mplatrforma.amr.entity.*;
import com.mresearch.databank.shared.PublicationDTO_Light;
import com.mresearch.databank.shared.StartupBundleDTO;
import com.mresearch.databank.shared.UserAccountDTO;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author reshet
 */
@WebService
@Stateless(mappedName="UserAccountRemoteBean",name="UserAccountRemoteBean")
public class UserAccountSessionBean implements UserAccountBeanRemote{

    
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB AdminPubBeanRemote pubbean;
   
    public static String PUBLICATION_TOPIC="topic";
    public static String CONSULTATION_TOPIC="topic";
    @Override
    public UserAccountDTO getUserAccount(String email, String password) {
       //initDefaults();
       //createDefaultDatabankStructure();
       //return new UserAccountDTO(email,email, password);
       //createDefaultDatabankVarStructure();
       //  createDefaultDatabankVarStructure();
       //createDefaultDatabankLawStructure();
       // initDefaults();
       // createDefaultStartPage();
       return UserAccount.toDTO(new UserAccount(em).getUserAccount(email, password));
    } 
    
     private void createDefaultDatabankStructure()
      {
          //DatabankStructure<SocioResearch> db = new DatabankStructure<SocioResearch>("socioresearch");
          //MetaUnitMultivalued<SocioResearch> root = new MetaUnitMultivalued<SocioResearch>("Socioresearch Metadata Structure");
          
          DatabankStructure db = new DatabankStructure("socioresearch");
         
          MetaUnitMultivaluedEntity root = new MetaUnitMultivaluedEntity("socioresearch","Socioresearch Metadata Structure",0);
          ArrayList<MetaUnit> arr = new ArrayList<MetaUnit>();
          
          arr.add(new MetaUnitString("name","Название исследования"));
          
          //MetaUnitMultivalued<SocioResearch> dates = new MetaUnitMultivalued<SocioResearch>("Даты исследования");
          MetaUnitMultivaluedStructure dates = new MetaUnitMultivaluedStructure("dates","Даты исследования");
          ArrayList<MetaUnit> arr_d = new ArrayList<MetaUnit>();
          arr_d.add(new MetaUnitDate("start_date","Дата начала полевого этапа"));
          arr_d.add(new MetaUnitDate("end_date","Дата конца полевого этапа"));
          dates.setSub_meta_units(arr_d);
          arr.add(dates);
          
          //MetaUnitMultivalued<SocioResearch> gen_geath = new MetaUnitMultivalued<SocioResearch>("Генеральная совокупность");          
          MetaUnitMultivaluedEntity gen_geath = new MetaUnitMultivaluedEntity("gen_geath","Генеральная совокупность",0);          
          arr.add(gen_geath);
          
          arr.add(new MetaUnitInteger("sel_size","Объем выборки"));
          
          //MetaUnitMultivalued<SocioResearch> selection = new MetaUnitMultivalued<SocioResearch>("Способ генерации выборки");          
          MetaUnitMultivaluedStructure selection = new MetaUnitMultivaluedStructure("generation_type","Способ генерации выборки");          
          ArrayList<MetaUnit> selection_variants = new ArrayList<MetaUnit>();
          selection.setSub_meta_units(selection_variants);
          
          ArrayList<MetaUnitEntityItem> selection_randomity = new ArrayList<MetaUnitEntityItem>();
          ArrayList<MetaUnitEntityItem> selection_levels = new ArrayList<MetaUnitEntityItem>();
          
          MetaUnitMultivaluedEntity randomity = new MetaUnitMultivaluedEntity("randomity","случайность", 0);
          MetaUnitMultivaluedEntity levels = new MetaUnitMultivaluedEntity("levels","ступенчастость", 0);
          
          
          
          selection_levels.add(new MetaUnitEntityItem("одноступенчатая"));
          selection_levels.add(new MetaUnitEntityItem("многоступенчатая"));
          selection_randomity.add(new MetaUnitEntityItem("случайная"));
          selection_randomity.add(new MetaUnitEntityItem("неслучайная"));
          
          randomity.setItems(selection_randomity);
          levels.setItems(selection_levels);
          
          selection_variants.add(randomity);
          selection_variants.add(levels);
          
          arr.add(selection);
          
          MetaUnitMultivaluedEntity organization = new MetaUnitMultivaluedEntity("organization","Организация", 0);
          ArrayList<MetaUnit> org_subfields = new ArrayList<MetaUnit>();
          org_subfields.add(new MetaUnitString("name","Название"));
          org_subfields.add(new MetaUnitString("phone","Телефон"));
          org_subfields.add(new MetaUnitString("adress","Адрес"));
          organization.setSub_meta_units(org_subfields);
          
          ArrayList<MetaUnitEntityItem> orgs = new ArrayList<MetaUnitEntityItem>();
          HashMap<String,String> org1m,org2m;
          org1m = new HashMap<String, String>();
          org2m = new HashMap<String, String>();
          org1m.put("name", "КМИС");
          org1m.put("phone", "0445632234");
          
          org2m.put("name", "Альянс");
          org2m.put("phone", "04412121212");
          
          MetaUnitEntityItem org1,org2;
          org1 = new MetaUnitEntityItem("КМИС");
          org1.setMapped_values(org1m);
          
          ArrayList<MetaUnitEntityItem> subitems = new ArrayList<MetaUnitEntityItem>();
          subitems.add(new MetaUnitEntityItem("подразделение Киева"));
          org1.setSubitems(subitems);
          
          org2 = new MetaUnitEntityItem("Альянс");
          org2.setMapped_values(org2m);
          orgs.add(org1);
          orgs.add(org2);
          
          organization.setItems(orgs);
          
          MetaUnitMultivaluedStructure orgs_in_research = new MetaUnitMultivaluedStructure("orgs", "Организации");
          
          MetaUnitMultivaluedStructure orgs_order = new MetaUnitMultivaluedStructure("org_order", "Организация-заказчик");
          MetaUnitMultivaluedStructure orgs_impl = new MetaUnitMultivaluedStructure("org_impl", "Организация-исполнитель");
          ArrayList<MetaUnit> orgs_order_sub = new ArrayList<MetaUnit>();
          orgs_order_sub.add(organization);
          ArrayList<MetaUnit> orgs_impl_sub = new ArrayList<MetaUnit>();
          orgs_impl_sub.add(organization);
          orgs_order.setSub_meta_units(orgs_order_sub);
          orgs_impl.setSub_meta_units(orgs_impl_sub);
          
          
          
          ArrayList<MetaUnit> orgs_variants = new ArrayList<MetaUnit>();
          orgs_variants.add(orgs_order);
          orgs_variants.add(orgs_impl);
          orgs_in_research.setSub_meta_units(orgs_variants);
          
          arr.add(orgs_in_research);
          
          
          //MetaUnitMultivalued<SocioResearch> researchers = new MetaUnitMultivalued<SocioResearch>("Исследователи, связанные с проектом");          
          MetaUnitMultivalued researchers = new MetaUnitMultivaluedEntity("researchers","Исследователи, связанные с проектом",1);          
          ArrayList<MetaUnit> arr_r = new ArrayList<MetaUnit>();
          researchers.setSub_meta_units(arr_r);
          
          root.setSub_meta_units(arr);
//          em.persist(root);
          db.setRoot(root);
          em.persist(db);
          
          int b = 2;
      }
    private void createDefaultDatabankVarStructure()
      {
          //DatabankStructure<SocioResearch> db = new DatabankStructure<SocioResearch>("socioresearch");
          //MetaUnitMultivalued<SocioResearch> root = new MetaUnitMultivalued<SocioResearch>("Socioresearch Metadata Structure");
          
          DatabankStructure db = new DatabankStructure("sociovar");
         
          MetaUnitMultivaluedEntity root = new MetaUnitMultivaluedEntity("sociovar","Var Metadata Structure",0);
          ArrayList<MetaUnit> arr = new ArrayList<MetaUnit>();
          
          arr.add(new MetaUnitString("name","Текст вопроса"));
          //arr.add(new MetaUnitString("code","Код переменной"));
          
          //arr.add(new MetaUnitString("alt_codes","Коды альтернатив"));
          arr.add(new MetaUnitString("alt_values","Тексты альтернатив"));
          
//          MetaUnitMultivaluedStructure alternatives = new MetaUnitMultivaluedStructure("alt","Даты исследования");
//          ArrayList<MetaUnit> arr_d = new ArrayList<MetaUnit>();
//          arr_d.add(new MetaUnitMultivaluedEntity("code","Код альтернативы",0));
//          arr_d.add(new MetaUnitMultivaluedEntity("value","Текст альтернативы",0));
//          alternatives.setSub_meta_units(arr_d);
//          arr.add(alternatives);
          arr.add(new MetaUnitMultivaluedEntity("concept","Концепты",1));
         
          
          root.setSub_meta_units(arr);
//          em.persist(root);
          db.setRoot(root);
          em.persist(db);
          
          int b = 2;
      }
        private void createDefaultDatabankLawStructure()
      {
          //DatabankStructure<SocioResearch> db = new DatabankStructure<SocioResearch>("socioresearch");
          //MetaUnitMultivalued<SocioResearch> root = new MetaUnitMultivalued<SocioResearch>("Socioresearch Metadata Structure");
          
          DatabankStructure db = new DatabankStructure("law");
         
          MetaUnitMultivaluedEntity root = new MetaUnitMultivaluedEntity("law","Law Metadata Structure",0);
          ArrayList<MetaUnit> arr = new ArrayList<MetaUnit>();
          
          arr.add(new MetaUnitString("name","Название закона"));
          arr.add(new MetaUnitString("number","Номер закона"));
          arr.add(new MetaUnitString("contents","Краткое содержание"));
       
          arr.add(new MetaUnitDate("date","Дата принятия"));
          arr.add(new MetaUnitDate("date_decline","Дата отмены"));
          
          MetaUnitMultivaluedEntity auth = new MetaUnitMultivaluedEntity("authors","Авторы", 1);
          arr.add(auth);
          
          MetaUnitMultivaluedEntity catalog = new MetaUnitMultivaluedEntity("catalog","Каталогизация", 0);
          arr.add(catalog);
          
          
          
//          MetaUnitMultivaluedStructure alternatives = new MetaUnitMultivaluedStructure("alt","Даты исследования");
//          ArrayList<MetaUnit> arr_d = new ArrayList<MetaUnit>();
//          arr_d.add(new MetaUnitMultivaluedEntity("code","Код альтернативы",0));
//          arr_d.add(new MetaUnitMultivaluedEntity("value","Текст альтернативы",0));
//          alternatives.setSub_meta_units(arr_d);
//          arr.add(alternatives);
          root.setSub_meta_units(arr);
//          em.persist(root);
          db.setRoot(root);
          em.persist(db);
          
          int b = 2;
      }
        
        private void createDefaultDatabankPubStructure()
      {
          //DatabankStructure<SocioResearch> db = new DatabankStructure<SocioResearch>("socioresearch");
          //MetaUnitMultivalued<SocioResearch> root = new MetaUnitMultivalued<SocioResearch>("Socioresearch Metadata Structure");
          
          DatabankStructure db = new DatabankStructure("publication");
         
          MetaUnitMultivaluedEntity root = new MetaUnitMultivaluedEntity("publication","Publication Metadata Structure",0);
          ArrayList<MetaUnit> arr = new ArrayList<MetaUnit>();
          
          arr.add(new MetaUnitString("name","Название публикации"));
          arr.add(new MetaUnitString("subheading","Доп. информация, источник"));
          arr.add(new MetaUnitString("contents","Содержание"));
       
          arr.add(new MetaUnitDate("date","Дата публикации"));
          
          MetaUnitMultivaluedEntity auth = new MetaUnitMultivaluedEntity("authors","Авторы", 1);
          arr.add(auth);
          
          MetaUnitMultivaluedEntity catalog = new MetaUnitMultivaluedEntity(PUBLICATION_TOPIC,"Рубрика", 0);
          arr.add(catalog);
          
          
          
//          MetaUnitMultivaluedStructure alternatives = new MetaUnitMultivaluedStructure("alt","Даты исследования");
//          ArrayList<MetaUnit> arr_d = new ArrayList<MetaUnit>();
//          arr_d.add(new MetaUnitMultivaluedEntity("code","Код альтернативы",0));
//          arr_d.add(new MetaUnitMultivaluedEntity("value","Текст альтернативы",0));
//          alternatives.setSub_meta_units(arr_d);
//          arr.add(alternatives);
          root.setSub_meta_units(arr);
//          em.persist(root);
          db.setRoot(root);
          em.persist(db);
          
          int b = 2;
      }

      private void createDefaultDatabankJuryStructure()
      {
          //DatabankStructure<SocioResearch> db = new DatabankStructure<SocioResearch>("socioresearch");
          //MetaUnitMultivalued<SocioResearch> root = new MetaUnitMultivalued<SocioResearch>("Socioresearch Metadata Structure");
          
          DatabankStructure db = new DatabankStructure("consultation");
         
          MetaUnitMultivaluedEntity root = new MetaUnitMultivaluedEntity("consultation","consultation Metadata Structure",0);
          ArrayList<MetaUnit> arr = new ArrayList<MetaUnit>();
          
          arr.add(new MetaUnitString("question","Вопрос"));
          arr.add(new MetaUnitString("asker","Имя задавшего вопрос пользователя"));
          arr.add(new MetaUnitString("answer","Ответ специалиста"));
          arr.add(new MetaUnitDate("date_ask","Дата вопроса"));
          arr.add(new MetaUnitDate("date_ans","Дата ответа"));
         
          MetaUnitMultivaluedEntity catalog = new MetaUnitMultivaluedEntity(CONSULTATION_TOPIC,"Рубрика", 0);
          arr.add(catalog);
          
          
          
//          MetaUnitMultivaluedStructure alternatives = new MetaUnitMultivaluedStructure("alt","Даты исследования");
//          ArrayList<MetaUnit> arr_d = new ArrayList<MetaUnit>();
//          arr_d.add(new MetaUnitMultivaluedEntity("code","Код альтернативы",0));
//          arr_d.add(new MetaUnitMultivaluedEntity("value","Текст альтернативы",0));
//          alternatives.setSub_meta_units(arr_d);
//          arr.add(alternatives);
          root.setSub_meta_units(arr);
//          em.persist(root);
          db.setRoot(root);
          em.persist(db);
          
          int b = 2;
      }
       private void createDefaultStartPage()
      {
          //DatabankStructure<SocioResearch> db = new DatabankStructure<SocioResearch>("socioresearch");
          //MetaUnitMultivalued<SocioResearch> root = new MetaUnitMultivalued<SocioResearch>("Socioresearch Metadata Structure");
          
          DatabankStartPage p = new DatabankStartPage();
          p.setPubs_last_show(new Long(5));
          em.persist(p);
      }
    @Override
    public UserAccountDTO updateAccountResearchState(UserAccountDTO dto) {
        UserAccount account;
        UserAccountDTO returnBack = dto;
        account = em.find(UserAccount.class,dto.getId());
        if (account != null)
        {
                account.updateAccountResearchState(dto);
                returnBack = UserAccount.toDTO(account);
                
        }
        return returnBack;
    }

    @Override
    public void initDefaults() {
        //new UserAccount(em).createDefaults();
        //createDefaultDatabankStructure();
       // createDefaultDatabankVarStructure();
        //createDefaultDatabankLawStructure();
       // createDefaultDatabankPubStructure();
     //   createDefaultDatabankJuryStructure();
    }

    @Override
    public UserAccountDTO getDefaultUser() {
            return UserAccount.toDTO(new UserAccount(em).getDefaultUser());
    }

    @Override
    public StartupBundleDTO getStartupContent() {
        
        DatabankStartPage d = DatabankStartPage.getStartPageSingleton(em);
        ArrayList<PublicationDTO_Light> pubs = pubbean.getPublications(d.getPubs_last_show().intValue(), 0);
        StartupBundleDTO dto = d.toDTO();
        dto.setIndex_last(pubs);
        return dto;
    }
    
}
