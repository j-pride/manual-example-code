package joins;

import java.sql.SQLException;

import pm.pride.SQL;
import quickstart.Customer;
import util.ResourceAccessorExampleConfig;

public class JoinsClient {

	public static final String CUSTOMER_ALIAS = "cst";
	public static final String ADDRESS_ALIAS = "addr";
	public static final String CUSTOMER_ADDRESS_JOIN_CONDITION = SQL.build(
			"@adr.@customer_id = @cst.@id",
			ADDRESS_ALIAS, Address.COL_CUSTOMER_ID, CUSTOMER_ALIAS, Customer.COL_ID);
	
	public JoinsClient() throws Exception {
		ResourceAccessorExampleConfig.initPriDE();
		createCustomerWithAndWithoutAddress();
		queryAllCustomerNamesWithCity();
		queryPaddyWithCity();
		queryAllCustomers();
		queryAllCustomersWithAddress();
	}
	
	private void queryAllCustomerNamesWithCity() throws SQLException {
		new CustomerNameAndCity().queryAll().stream(CustomerNameAndCity.class).forEach(c -> System.out.println(c));
	}

	private void queryAllCustomers() throws Exception {
		new CustomerWithOptionalAddress().queryAll().stream(CustomerWithOptionalAddress.class).forEach(c -> System.out.println(c));
	}

	private void queryAllCustomersWithAddress() throws Exception {
		new CustomerWithMandatoryAddress().queryAll().stream(CustomerWithMandatoryAddress.class).forEach(c -> System.out.println(c));
	}

	private void queryPaddyWithCity() throws SQLException {
		CustomerWithCity cwc = new CustomerWithCity(1);
		System.out.println(cwc);
	}

	private void createCustomerWithAndWithoutAddress() throws SQLException {
		try {
			Customer paddy = new Customer(1, "Fingal", "Paddy");
			Address paddysAddress = new Address(paddy, "London", "Downing Street");
			Customer mary = new Customer(2, "Poppins", "Mary");
			mary.commit();
		}
		catch(SQLException sqlx) {} // Don't care - we assume the data was already present
	}
	
	public static void main(String[] args) throws Exception {
		new JoinsClient();
	}

}
