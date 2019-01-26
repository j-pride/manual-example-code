package boot;

import java.util.Properties;

import javax.naming.InitialContext;

import pm.pride.DatabaseFactory;
import pm.pride.ResourceAccessor;
import pm.pride.ResourceAccessorJEE;

public class MinimalBootstrapJEE {

	public static void init() throws Exception {
		InitialContext ic = new InitialContext();
		String dbtype = ic.lookup("java:global/myapp/mydbtype").toString();
		
		Properties props = new Properties();
		props.setProperty(ResourceAccessor.Config.DBTYPE, dbtype);
		ResourceAccessor re = new ResourceAccessorJEE(props);
		
		DatabaseFactory.setDatabaseName("java:global/myapp/mydb");
		
		DatabaseFactory.setResourceAccessor(re);
	}
}
