package joins;

import util.AbstractCreateTable;

public class CreateAddressTable extends AbstractCreateTable {
	public static void main(String[] args) throws Exception {
		createTable("ADDRESS",
			"customer_id integer not null",
			"street varchar(30) not null",
			"city varchar(30) not null"
		);
	}
}
