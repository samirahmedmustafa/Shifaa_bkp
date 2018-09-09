package entity.domain;

import entity.domain.Clinic;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-09T13:14:43")
@StaticMetamodel(Doctor.class)
public class Doctor_ { 

    public static volatile SingularAttribute<Doctor, String> image;
    public static volatile SingularAttribute<Doctor, String> qualifications;
    public static volatile SingularAttribute<Doctor, String> name;
    public static volatile SingularAttribute<Doctor, Long> id;
    public static volatile SingularAttribute<Doctor, Clinic> clinic;

}