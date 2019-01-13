package joins;

import java.sql.SQLException;

import pm.pride.JoinRecordDescriptor;
import pm.pride.MappedObject;
import pm.pride.RecordDescriptor;
import pm.pride.SQL;
import quickstart.Customer;
import static joins.JoinsClient.*;

public class CustomerNameAndCity extends MappedObject implements Cloneable {
    protected static final RecordDescriptor red =
    	new JoinRecordDescriptor(CustomerNameAndCity.class, Customer.TABLE, CUSTOMER_ALIAS)
			.row(Customer.COL_ID, "getId", "setId")
    		.row(Customer.COL_NAME, "getName", "setName")
    	  .join(Address.TABLE, ADDRESS_ALIAS, CUSTOMER_ADDRESS_JOIN_CONDITION)
    	    .row(Address.COL_CITY, "getCity", "setCity");

    public RecordDescriptor getDescriptor() { return red; }

    private int id;
    private String name;
    private String city;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public CustomerNameAndCity() {}
	
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	public String toString() {
		return getId() + ": " + getName() + ", " + getCity();
	}
}
