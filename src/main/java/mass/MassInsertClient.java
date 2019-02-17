package mass;

import java.util.Date;

import pm.pride.PreparedInsert;
import quickstart.Customer;
import util.ResourceAccessorExampleConfig;

public class MassInsertClient {

	public static void main(String[] args) throws Exception {
		ResourceAccessorExampleConfig.initPriDE();
		Customer customer = new Customer();
		int deletedCustomers = customer.deleteByExample();
		customer.commit();
		System.out.println("Deleted " + deletedCustomers + " customers");

		int amountOfCustomersToCreate = 200000;
		Date start = new Date();
		try (PreparedInsert insert = new PreparedInsert(customer.getDescriptor())) {
			for (int i = 0; i < amountOfCustomersToCreate; i++) {
				customer.setId(i);
				customer.setFirstName("Paddy-" + i);
				customer.setName("Fingal");
				//insert.execute(customer);
				insert.addBatch(customer);
			}
			insert.executeBatch();
			customer.commit();
		}
		Date end = new Date();
		long seconds = (end.getTime() - start.getTime()) / 1000;
		System.out.println(amountOfCustomersToCreate + " customers created in " + seconds + " second(s)");
	}
}
