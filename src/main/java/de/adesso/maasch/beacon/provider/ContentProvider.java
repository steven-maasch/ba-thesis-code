package de.adesso.maasch.beacon.provider;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import de.adesso.maasch.beacon.model.Beacon;
import de.adesso.maasch.beacon.model.BeaconPosition;
import de.adesso.maasch.beacon.model.Content;
import de.adesso.maasch.beacon.model.dao.BeaconDao;
import de.adesso.maasch.beacon.model.dao.BeaconPositionDao;

/**
 * @author Steven Maasch
 *
 */
@Component
public class ContentProvider implements IContentProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentProvider.class);
	
	@Autowired
	BeaconDao beaconDao;
	
	@Autowired
	BeaconPositionDao beaconPositionDao;
	
	@Override
	public List<Content> getContentsForBeaconProperties(final String uuid, 
			final Integer major, final Integer minor) {
		try {
			final Beacon beacon = beaconDao.findByUuidAndMajorAndMinor(uuid, major, minor);
			final BeaconPosition beaconPosition = beaconPositionDao.findByBeacon(beacon);
			LOGGER.info("Getting contents for beacon position {}", beaconPosition);
			return beaconPosition.getContents();
		} catch(EmptyResultDataAccessException e) {
			LOGGER.info("No content for beacon with uuid: " + uuid + ", major: " + major + ", minor: " + minor, e);
			return null;
		}
	}

}
