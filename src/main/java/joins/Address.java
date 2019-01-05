package joins;

import java.sql.SQLException;
import pm.pride.*;

/**
 * @author jlessner
 */
public class Address extends MappedObject implements Cloneable, java.io.Serializable {
    public static final String TABLE = "ADDRESS";
    public static final String COL_CUSTOMER_ID = "customer_id";
    public static final String COL_STREET = "street";
    public static final String COL_CITY = "city";

    protected static final RecordDescriptor red = new RecordDescriptor
        (Address.class, TABLE, null, new String[][] {
            { COL_CUSTOMER_ID,   "getCustomerId",   "setCustomerId" },
            { COL_STREET,   "getStreet",   "setStreet" },
            { COL_CITY,   "getCity",   "setCity" },
        });

    public RecordDescriptor getDescriptor() { return red; }


    private int customerId;
    private String street;
    private String city;

    // Read access functions
    public int getCustomerId()   { return customerId; }
    public String getStreet()   { return street; }
    public String getCity()   { return city; }

    // Write access functions
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setStreet(String street) { this.street = street; }
    public void setCity(String city) { this.city = city; }


    public Address() {}

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

