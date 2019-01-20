package locks;

import java.sql.SQLException;

import pm.pride.DatabaseFactory;
import pm.pride.ResourceAccessor.DBType;
import util.ResourceAccessorExampleConfig;

public class LocksClient {

	public LocksClient() throws Exception {
		ResourceAccessorExampleConfig.initPriDE();
		
		optimisticLockingByVersionColumn();
		pessimisticLockingBySelectForUpdate();
	}
	
	private void optimisticLockingByVersionColumn() throws SQLException {
		OptimisticCustomer bruce = new OptimisticCustomer();
		bruce.setId(1);
		if (!bruce.find()) {
			bruce = new OptimisticCustomer(1, "Lee", "Bruce");
		}
		System.out.println(bruce);
		bruce.setName(bruce.getName() + "e");
		bruce.update();
		System.out.println(bruce);
		bruce.update();
		System.out.println(bruce);
		bruce.update();
		System.out.println(bruce);
		bruce.commit();
	}

	private void pessimisticLockingBySelectForUpdate() throws SQLException {
		if (DatabaseFactory.getDatabase().getDBType().equals(DBType.SQLITE)) {
			System.err.println("SQLite doesn't support SELECT FOR UPDATE, " +
				"following pessimistic lock for a single record will propably not work.");
		}
		
		PessimisticCustomer bruce = new PessimisticCustomer();
		bruce.setId(1);
		if (!bruce.exists()) {
			bruce = new PessimisticCustomer(1, "Lee", "Bruce");
		}
		bruce.find(); // Select for Update
		bruce.commit(); // Lock release
	}

	public static void main(String[] args) throws Exception {
		new LocksClient();
	}
}
