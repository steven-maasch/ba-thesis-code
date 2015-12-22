package de.adesso.maasch.beacon.model.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessResourceFailureException;

import de.adesso.maasch.beacon.model.Content;

/**
 * @author Steven Maasch
 *
 */
public interface IContentDao {

	public List<Content> getAllContents() throws DataAccessResourceFailureException;
	
	public List<Content> getContentsByIds(Collection<Integer> ids);
	
	public Content getContentById(Integer id);
	
}
