package de.adesso.maasch.beacon.model;

import java.util.Map;

/**
 * @author Steven Maasch
 *
 */
public class Config {
	
	private Map<String, String> configTuples;
	
	public Config() {

	}
	
	public Config(Map<String, String> configTuples) {
		this.configTuples = configTuples;
	}
	
	public String getValueForKey(String key) {
		return configTuples.get(key);
	}
	
	public Map<String, String> getConfigTuples() {
		return configTuples;
	}

	public void setConfigTuples(Map<String, String> configTuples) {
		this.configTuples = configTuples;
	}
	
}
