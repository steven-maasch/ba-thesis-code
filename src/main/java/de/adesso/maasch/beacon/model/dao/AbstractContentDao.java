package de.adesso.maasch.beacon.model.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.adesso.maasch.beacon.model.Content;

/**
 * @author Steven Maasch
 *
 */
public abstract class AbstractContentDao implements IContentDao {
	
	@Override
	public List<Content> getContentsByIds(Collection<Integer> ids) {
		
		if (ids == null) {
			return Collections.emptyList();
		}
		
		final Set<Integer> idSet = new HashSet<Integer>(ids);
		final List<Content> allContents = new LinkedList<Content>(getAllContents());

		final Iterator<Content> it = allContents.iterator();
		
		while (it.hasNext()) {
			if (!idSet.contains(it.next().getFsId())) {
				it.remove();
			}			
		}
		return allContents;
	}
	
	@Override
	public Content getContentById(Integer id) {
		for (Content content : getAllContents()) {
			if (content.getFsId().equals(id)) {
				return content;
			}
		}
		return null;
	}	
}
