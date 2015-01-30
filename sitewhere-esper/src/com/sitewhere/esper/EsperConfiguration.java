package com.sitewhere.esper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;


/**
 * Setup the Esper configuration details.
 * @author Steve Buster
 *
 */
public class EsperConfiguration implements InitializingBean,LifecycleListener {
	private static Logger LOGGER = Logger.getLogger(EsperConfiguration.class.getName());
	
	//Singleton instance
	static EPServiceProvider instance = null;
	private final static String DEFAULT_FILE_NAME="esper.cfg.xml";//Taken from the Esper documentation.
	
	private String configFileName = DEFAULT_FILE_NAME;
	
	
	@Override
	public void afterPropertiesSet()  {
		LOGGER.info("Starting Esper instance...");
		Configuration c = new Configuration();
		c.configure();
		instance = EPServiceProviderManager.getDefaultProvider(c);

	}

	@Override
	public void stateChanged(LifecycleEvent arg0) {
		LOGGER.info("Esper State Changeed!!!");
		
	}

	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}
	
	public EPServiceProvider getServiceProvider(){
		return this.instance;
	}
	

}
