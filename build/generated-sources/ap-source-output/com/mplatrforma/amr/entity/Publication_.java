package com.mplatrforma.amr.entity;

import com.mplatrforma.amr.entity.MetaUnitEntityItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-05-25T10:35:14")
@StaticMetamodel(Publication.class)
public class Publication_ { 

    public static volatile SingularAttribute<Publication, Long> id;
    public static volatile SingularAttribute<Publication, MetaUnitEntityItem> entity_item;
    public static volatile SingularAttribute<Publication, String> contents;
    public static volatile SingularAttribute<Publication, String> subheading;
    public static volatile SingularAttribute<Publication, Date> date_publ;
    public static volatile SingularAttribute<Publication, String> name;
    public static volatile SingularAttribute<Publication, Long> enclosure_key;

}