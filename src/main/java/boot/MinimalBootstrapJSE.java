package boot;

import java.util.Properties;

import pm.pride.DatabaseFactory;
import pm.pride.ResourceAccessor;
import pm.pride.ResourceAccessorJSE;
import pm.pride.SQL;

public class MinimalBootstrapJSE {

	public static void main(String[] args) throws Exception {
		
		Properties props = new Properties();
		props.setProperty(ResourceAccessor.Config.DBTYPE, "sqlite");
		props.setProperty(ResourceAccessor.Config.DRIVER, "org.sqlite.JDBC");
		ResourceAccessor re = new ResourceAccessorJSE(props);
		
		DatabaseFactory.setDatabaseName("jdbc:sqlite:pride.examples.db");
		
		DatabaseFactory.setResourceAccessor(re);
		
		DatabaseFactory.getDatabase().sqlExecute("select * from customer");
		
		System.out.println(SQL.systime().getTime());
	}
}
