package joins;

import java.sql.SQLException;

import pm.pride.JoinRecordDescriptor;
import pm.pride.RecordDescriptor;
import pm.pride.SQL;
import pm.pride.WhereCondition;
import quickstart.Customer;
import util.ResourceAccessorExampleConfig;

public class JoinsClient {

	public static final String CUSTOMER_ALIAS = "cst";
	public static final String ADDRESS_ALIAS = "addr";
	public static final String CUSTOMER_ADDRESS_JOIN_CONDITION = SQL.build(
			"@addr.@customer_id = @cst.@id",
			ADDRESS_ALIAS, Address.COL_CUSTOMER_ID, CUSTOMER_ALIAS, Customer.COL_ID);
	
	public JoinsClient() throws Exception {
		ResourceAccessorExampleConfig.initPriDE();
		createCustomerWithAndWithoutAddress();
		queryAllCustomerNamesWithCity();
		queryCustomerOneWithCity();
		queryCustomersWithCityFromLondon();
		queryCustomersFromLondon();
		queryAllCustomers();
		queryAllCustomersWithAddress();
	}
	
	private void queryCustomersFromLondon() throws SQLException {
		System.out.println("\nCustomers from London by an ad-hoc descriptor:");
		Customer customer = new Customer();
		RecordDescriptor customerJoinedWithAddress =
			new JoinRecordDescriptor(customer.getDescriptor(), CUSTOMER_ALIAS)
				.join(Address.TABLE, ADDRESS_ALIAS, CUSTOMER_ADDRESS_JOIN_CONDITION);
		WhereCondition onlyLondon = new WhereCondition().and("city", "London");
		customer.joinQuery(customerJoinedWithAddress, onlyLondon)
			.stream(Customer.class).forEach(c -> System.out.println(c));
	}

	private void queryCustomersWithCityFromLondon() throws SQLException {
		System.out.println("\nCustomers from London:");
		CustomerWithCity cwc = new CustomerWithCity();
		cwc.setCity("London");
		cwc.queryByExample(Address.COL_CITY)
			.stream(CustomerWithCity.class)
			.forEach(c -> System.out.println(c));
	}

	private void queryAllCustomerNamesWithCity() throws SQLException {
		System.out.println("\nCustomer names and citys:");
		new CustomerNameAndCity().queryAll().stream(CustomerNameAndCity.class).forEach(c -> System.out.println(c));
	}

	private void queryAllCustomers() throws Exception {
		System.out.println("\nAll customers, with and without address (outer join):");
		new CustomerWithOptionalAddress().queryAll().stream(CustomerWithOptionalAddress.class).forEach(c -> System.out.println(c));
	}

	private void queryAllCustomersWithAddress() throws Exception {
		System.out.println("\nAll customers with associated address (inner join)");
		new CustomerWithAddress().queryAll().stream(CustomerWithAddress.class).forEach(c -> System.out.println(c));
	}

	private void queryCustomerOneWithCity() throws SQLException {
		System.out.println("\nCustomer No. 1 with its city:");
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
