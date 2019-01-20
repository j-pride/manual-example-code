package inherit;

import java.sql.SQLException;
import pm.pride.*;

/**
 * @author jlessner
 */
public class DerivedCustomerAdapter extends inherit.AbstractAdapter {
    public static final String TABLE = "CUSTOMER";
    public static final String COL_NAME = "name";
    public static final String COL_FIRST_NAME = "first_name";

    protected static final RecordDescriptor red =
        new RecordDescriptor(DerivedCustomerEntity.class, TABLE, inherit.AbstractAdapter.red)
            .row( COL_NAME, "getName", "setName" )
            .row( COL_FIRST_NAME, "getFirstName", "setFirstName" )
            .key( COL_ID );

    public RecordDescriptor getDescriptor() { return red; }

    DerivedCustomerAdapter(DerivedCustomerEntity entity) { super(entity); }
}
