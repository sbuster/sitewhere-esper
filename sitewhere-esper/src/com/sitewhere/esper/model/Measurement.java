package com.sitewhere.esper.model;
/**
 * Create a new data container to make is simplier to use in ESPER.  For Esper, this should be a immutable object.
 * 
 * @author BUSTESJ
 *
 */
public class Measurement {
	private String siteId = null;
	private String assetId = null;
	private String hardwareId = null;
	private String name = null;
	private double value = 0;
	
	public Measurement(String siteId,String assetId,String hardwareId,String name,double value){
		this.siteId=siteId;
		this.assetId=assetId;
		this.hardwareId=hardwareId;
		this.name=name;
		this.value=value;
		
	}
	public String getSiteId() {
		return siteId;
	}
	
	public String getAssetId() {
		return assetId;
	}
	
	public String getHardwareId() {
		return hardwareId;
	}
	
	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;
	}
	
	
	
	

}
