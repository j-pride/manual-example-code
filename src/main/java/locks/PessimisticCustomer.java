package locks;

import java.sql.SQLException;

import pm.pride.RecordDescriptor;
import quickstart.Customer;

public class PessimisticCustomer extends PessimisticObject {
    public static final String TABLE = "CUSTOMER";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_FIRST_NAME = "first_name";

    protected static final RecordDescriptor red =
        new RecordDescriptor(PessimisticCustomer.class, TABLE, null)
            .row( COL_ID, "getId", "setId" )
            .row( COL_NAME, "getName", "setName" )
            .row( COL_FIRST_NAME, "getFirstName", "setFirstName" )
            .key( COL_ID );

    public RecordDescriptor getDescriptor() { return red; }

    private int id;
    private String name;
    private String firstName;

    // Read access functions
    public int getId()   { return id; }
    public String getName()   { return name; }
    public String getFirstName()   { return firstName; }

    // Write access functions
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public PessimisticCustomer(int id) throws SQLException {
        setId(id);
        findXE();
    }

    public PessimisticCustomer(int id, String name, String firstName) throws SQLException {
    	this.id = id;
    	this.name = name;
    	this.firstName = firstName;
    	create();
    }
    
    public PessimisticCustomer() {}

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
    	return id + ": " + name + ", " + firstName;    	
    }
	
}
