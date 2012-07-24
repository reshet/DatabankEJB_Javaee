package com.mplatrforma.amr.entity;

import com.mplatrforma.amr.entity.MetaUnitEntityItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-07-23T17:41:57")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Long> id;
    public static volatile SingularAttribute<Consultation, MetaUnitEntityItem> entity_item;
    public static volatile SingularAttribute<Consultation, String> answer;
    public static volatile SingularAttribute<Consultation, String> question;
    public static volatile SingularAttribute<Consultation, Integer> published;
    public static volatile SingularAttribute<Consultation, Date> date_ask;
    public static volatile SingularAttribute<Consultation, Date> date_ans;

}