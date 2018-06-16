package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-06-15T08:42:46")
@StaticMetamodel(Books.class)
public class Books_ { 

    public static volatile SingularAttribute<Books, String> author;
    public static volatile SingularAttribute<Books, String> title;
    public static volatile SingularAttribute<Books, String> press;
    public static volatile SingularAttribute<Books, Double> price;
    public static volatile SingularAttribute<Books, String> isbn;

}