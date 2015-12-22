package de.adesso.maasch.beacon.provider;

import de.adesso.maasch.beacon.model.BeaconPosition;

public interface IBeaconPositionProvider {

	public BeaconPosition getBeaconPositionForBeaconProps(String uuid, Integer major, Integer minor);
	
}
