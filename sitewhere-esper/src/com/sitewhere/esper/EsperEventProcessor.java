package com.sitewhere.esper;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.sitewhere.esper.listener.MeasurementListener;
import com.sitewhere.esper.model.Measurement;
import com.sitewhere.hazelcast.HazelcastEventProcessor;
import com.sitewhere.rest.model.device.event.DeviceMeasurement;
import com.sitewhere.rest.model.device.event.processor.OutboundEventProcessor;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.event.DeviceEventType;
import com.sitewhere.spi.device.event.IDeviceMeasurements;

/**
 * Esper Event Processor to send events into the Esper Engine
 * @author Steve Buster
 *
 */
public class EsperEventProcessor extends OutboundEventProcessor {
	private EsperConfiguration configuration = null;
	private static Logger LOGGER = Logger.getLogger(EsperEventProcessor.class);
	

	@Override
	public void stop() throws SiteWhereException {
		
		super.stop();
	}
	
	/**
	 * Add Esper query statements here.  Need to extract these to a config file or something.
	 */
	@Override
	public void start() throws SiteWhereException {
		
		LOGGER.info("starting EsperEventProcessor");
		EPStatement stmt = configuration.getServiceProvider().getEPAdministrator().createEPL("select * from DeviceMeasurement");
		stmt.addListener(new MeasurementListener());
	}
	@Override
	public void onAlert(com.sitewhere.spi.device.event.IDeviceAlert alert) throws SiteWhereException {
		System.out.println(alert.toString());
		
	}
	
	@Override
	public void onLocation(com.sitewhere.spi.device.event.IDeviceLocation location) throws SiteWhereException {
		
		System.out.println(location.toString());
	};
	
	@Override
	public void onCommandInvocation(com.sitewhere.spi.device.event.IDeviceCommandInvocation invocation) throws SiteWhereException {
		
		System.out.println(invocation.toString());
	};
	
	
	@Override
	public void onMeasurements(IDeviceMeasurements measurements) throws SiteWhereException {
		LOGGER.info("EsperEventProcessor.onMessage...");
		if(measurements.getEventType()==DeviceEventType.Measurements){
			Map<String,Double> m = measurements.getMeasurements();
			Set<String> keys = m.keySet();
			for(String k:keys){
				Measurement meas = new Measurement(measurements.getAssetId(),null,k,null,m.get(k).doubleValue());
				//DeviceMeasurement d = new DeviceMeasurement();
				//d.setName(k);
				//d.setValue(m.get(k));
				configuration.getServiceProvider().getEPRuntime().sendEvent(meas);
			}
		}
	}

	public EsperConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(EsperConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
}
