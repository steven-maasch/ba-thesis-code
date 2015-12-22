package de.adesso.maasch.beacon.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.springframework.stereotype.Component;

/**
 * @author Steven Maasch
 *
 */
@Component
public class Content {

	private Integer fsId;
	
	private String message;
	
	private String title;
	
	private String relativeUrl;
	
	private List<String> keywords;
	
	/**
	 * Unix time stamp in milliseconds
	 */
	private Long lastModification;
	
	private Integer showAfterVisits;
	
	public Content() {

	}

	@XmlAttribute(name="fs_id")
	public Integer getFsId() {
		return fsId;
	}

	public void setFsId(Integer fsId) {
		this.fsId = fsId;
	}

	@XmlElement(name="message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name="relative_url")
	public String getRelativeUrl() {
		return relativeUrl;
	}

	public void setRelativeUrl(String relativeUrl) {
		this.relativeUrl = relativeUrl;
	}

	@XmlElementWrapper(name="keywords")
	@XmlElement(name="keyword")
	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	@XmlElement(name="last_modification")
	public Long getLastModification() {
		return lastModification;
	}

	public void setLastModification(Long lastModification) {
		this.lastModification = lastModification;
	}

	@XmlElement(name="show_after_visits")
	public Integer getShowAfterVisits() {
		return showAfterVisits;
	}

	public void setShowAfterVisits(Integer showAfterVisits) {
		this.showAfterVisits = showAfterVisits;
	}

	@Override
	public String toString() {
		return "Content [fsId=" + fsId + ", message=" + message + ", title="
				+ title + ", relativeUrl=" + relativeUrl + ", keywords="
				+ keywords + ", lastModification=" + lastModification
				+ ", showAfterVisits=" + showAfterVisits + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fsId == null) ? 0 : fsId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (fsId == null) {
			if (other.fsId != null)
				return false;
		} else if (!fsId.equals(other.fsId))
			return false;
		return true;
	}

}
