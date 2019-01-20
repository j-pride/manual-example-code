package locks;

import util.AbstractCreateTable;

public class CreateLockableCustomerTable extends AbstractCreateTable {
	public static void main(String[] args) throws Exception {
		createTable("LOCKABLECUSTOMER",
			"id integer not null primary key",
			"name varchar(20)",
			"first_name varchar(30)",
			"version integer not null"
		);
	}
}
