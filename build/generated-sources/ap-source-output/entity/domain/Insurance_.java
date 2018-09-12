package entity.domain;

import entity.domain.Clinic;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-12T21:11:33")
@StaticMetamodel(Insurance.class)
public class Insurance_ { 

    public static volatile SingularAttribute<Insurance, String> image;
    public static volatile ListAttribute<Insurance, Clinic> clinics;
    public static volatile SingularAttribute<Insurance, String> name;
    public static volatile SingularAttribute<Insurance, Long> id;

}