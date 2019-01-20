package inherit;

import java.sql.SQLException;
import pm.pride.*;

/**
 * @author jlessner
 */
abstract public class AbstractAdapter extends ObjectAdapter {
    public static final String COL_ID = "id";

    protected static final RecordDescriptor red = new RecordDescriptor
        (AbstractEntity.class, null, null)
	        .row( COL_ID, "getId", "setId" )
	        .key( COL_ID );

    public RecordDescriptor getDescriptor() { return red; }

    AbstractAdapter(AbstractEntity entity) { super(entity); }

}
