package de.adesso.maasch.beacon.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import de.adesso.maasch.beacon.model.Beacon;
import de.adesso.maasch.beacon.model.BeaconPosition;
import de.adesso.maasch.beacon.model.dao.BeaconDao;
import de.adesso.maasch.beacon.model.dao.BeaconPositionDao;

@Component
public class BeaconPositionProvider implements IBeaconPositionProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeaconPositionProvider.class);
	
	@Autowired
	BeaconDao beaconDao;
	
	@Autowired
	BeaconPositionDao beaconPositionDao;
	
	@Override
	public BeaconPosition getBeaconPositionForBeaconProps(String uuid,
			Integer major, Integer minor) {
		
		Beacon beacon = null;
		
		try {
			beacon = beaconDao.findByUuidAndMajorAndMinor(uuid, major, minor);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("No beacon found for uuid: " + uuid + ", major: " + major + ", minor: " + minor, e);
			return null;
		}
		
		BeaconPosition beaconPosition = null;
		
		try {	
			beaconPosition = beaconPositionDao.findByBeacon(beacon);
		} catch(EmptyResultDataAccessException e) {
			LOGGER.info("No beacon position for beacon " + beacon, e);
			return null;
		}
		
		return beaconPosition;
	}

}
