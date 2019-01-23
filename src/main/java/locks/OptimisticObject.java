package locks;

import java.sql.SQLException;

import pm.pride.MappedObject;
import pm.pride.RecordDescriptor;

abstract public class OptimisticObject extends MappedObject {
    public static final String COL_VERSION = "version";
	
    private long version; 
    public long getVersion() { return version; } 
    public void setVersion(long val) { version = val; }
    
    protected static RecordDescriptor red =
    	new RecordDescriptor(OptimisticObject.class, null, null)
            .row(COL_VERSION, "getVersion", "setVersion");
    
    public RecordDescriptor getDescriptor() { return red; }
    
    protected OptimisticObject() { version = 0; }

    @Override
    public int update() throws SQLException {
        version++;
        int numRows = update(where().and(COL_VERSION, version-1)); 
        if (numRows == 0) {
        	version--;
            throw new SQLException("optimistic lock error"); 
        }
        return numRows; 
    }
        
}
