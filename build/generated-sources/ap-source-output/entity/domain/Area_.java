package entity.domain;

import entity.domain.Clinic;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-09T13:14:43")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile ListAttribute<Area, Clinic> clinics;
    public static volatile SingularAttribute<Area, String> name;
    public static volatile SingularAttribute<Area, Long> id;

}