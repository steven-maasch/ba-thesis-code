package de.adesso.maasch.beacon.model;

import java.util.List;

/**
 * @author Steven Maasch
 *
 */
public class BeaconPosition {

	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private Beacon beacon;
	
	public Beacon getBeacon() {
		return beacon;
	}

	public void setBeacon(Beacon beacon) {
		this.beacon = beacon;
	}
	
	private List<Content> contents;
	
	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	
	public List<Content> getContents() {
		return contents;
	}
	
	private Facility facility;

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BeaconPosition other = (BeaconPosition) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BeaconPosition [id=" + id + ", name=" + name + ", beacon="
				+ beacon + ", contents=" + contents + ", facility=" + facility
				+ "]";
	}
	
}
