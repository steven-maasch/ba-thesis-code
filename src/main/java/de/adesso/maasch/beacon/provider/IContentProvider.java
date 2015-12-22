package de.adesso.maasch.beacon.provider;

import java.util.List;

import de.adesso.maasch.beacon.model.Content;

/**
 * @author Steven Maasch
 *
 */
public interface IContentProvider {

	public List<Content> getContentsForBeaconProperties(String uuid, Integer major, Integer minor);
	
}
