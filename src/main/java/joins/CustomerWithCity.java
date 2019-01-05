package joins;

import java.sql.SQLException;

import pm.pride.RecordDescriptor;
import pm.pride.SQL;
import quickstart.Customer;

public class CustomerWithCity extends Customer {
	public static final String TABLE_JOIN = SQL.build(
			"@CUSTOMER left outer join @ADDRESS on @ADDRESS.@CUSTOMER_ID = @CUSTOMER.@ID",
			TABLE, Address.TABLE, Address.COL_CUSTOMER_ID, COL_ID
			);

    protected static final RecordDescriptor red = new RecordDescriptor
            (CustomerWithCity.class, TABLE_JOIN, Customer.red, new String[][] {
                { Address.COL_CITY, "getCity", "setCity" },
            });

    public RecordDescriptor getDescriptor() { return red; }

    private String city;

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
    
	public CustomerWithCity(int id) throws SQLException {
		super(id);
	}
	
	public String toString() {
		return getFirstName() + " " + getName() + ", " + getCity();
	}
}
