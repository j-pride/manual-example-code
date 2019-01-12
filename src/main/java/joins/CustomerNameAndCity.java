package joins;

import java.sql.SQLException;

import pm.pride.JoinRecordDescriptor;
import pm.pride.MappedObject;
import pm.pride.RecordDescriptor;
import pm.pride.SQL;
import quickstart.Customer;

public class CustomerNameAndCity extends MappedObject implements Cloneable {
    protected static final RecordDescriptor red =
    	new JoinRecordDescriptor(CustomerNameAndCity.class, "CUSTOMER", "cst")
    		.row("name", "getName", "setName")
    	  .join("ADDRESS", "addr", "addr.customer_id = cst.id")
    	    .row("city", "getCity", "setCity");

    public RecordDescriptor getDescriptor() { return red; }

    private String city;
    private String name;

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public CustomerNameAndCity() {}
	
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	public String toString() {
		return getName() + ", " + getCity();
	}
}
