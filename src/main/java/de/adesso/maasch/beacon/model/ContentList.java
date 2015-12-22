package de.adesso.maasch.beacon.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Steven Maasch
 *
 */
@XmlRootElement(name="contents")
public class ContentList {

	private List<Content> contentList;
	
	public ContentList() {
		contentList = new ArrayList<Content>();
	}
	
	@XmlElement(name = "content")
	public List<Content> getContentList() {
		return this.contentList;
	}
	
}