package locks;

import java.sql.SQLException;

import pm.pride.MappedObject;

abstract public class PessimisticObject extends MappedObject {
	
    @Override
    public boolean find() throws SQLException {
    	return find(where().forUpdate());
    }

}
