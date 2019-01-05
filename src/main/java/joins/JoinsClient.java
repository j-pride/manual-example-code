package joins;

import java.sql.SQLException;

import quickstart.Customer;
import util.ResourceAccessorExampleConfig;

public class JoinsClient {

	public static void main(String[] args) throws Exception {
		ResourceAccessorExampleConfig.initPriDE();
		createCustomerWithAndWithoutAddress();
		CustomerWithCity cwc = new CustomerWithCity(1);
		System.out.println(cwc);
	}

	private static void createCustomerWithAndWithoutAddress() throws SQLException {
		try {
			Customer paddy = new Customer();
			paddy.setId(1);
			paddy.setName("Fingal");
			paddy.setFirstName("Paddy");
			paddy.create();
			
			Address paddysAddress = new Address();
			paddysAddress.setCustomerId(paddy.getId());
			paddysAddress.setCity("London");
			paddysAddress.setStreet("Downing Street");
			paddysAddress.create();
			
			Customer mary = new Customer();
			mary.setId(2);
			mary.setName("Poppins");
			mary.setFirstName("Mary");
			mary.create();
			
			mary.commit();
		}
		catch(SQLException sqlx) {} // Don't care - we assume the data was already present
	}
}
