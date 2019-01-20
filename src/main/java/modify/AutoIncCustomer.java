package modify;

import java.sql.SQLException;
import java.util.List;

import pm.pride.*;

/**
 * The code here is completely intentionally redundant to keep from coupling the quick start
 * example to this very special auto-increment example which may not of interest for every padawan.
 * 
 * @author jlessner
 */
public class AutoIncCustomer extends MappedObject implements Cloneable, java.io.Serializable {
    public static final String TABLE = "AUTOINCCUSTOMER";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_FIRST_NAME = "first_name";

    protected static final RecordDescriptor red =
        new RecordDescriptor(AutoIncCustomer.class, TABLE, null)
            .row( COL_ID, "getId", "setId" )
            .row( COL_NAME, "getName", "setName" )
            .row( COL_FIRST_NAME, "getFirstName", "setFirstName" )
            .key( COL_ID )
            .auto( COL_ID );

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


    // Re-constructor
    public AutoIncCustomer(int id) throws SQLException {
        setId(id);
        findXE();
    }

    public AutoIncCustomer() {}

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
    	return id + ": " + name + ", " + firstName;    	
    }
    
}
