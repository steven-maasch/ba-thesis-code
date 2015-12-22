package de.adesso.maasch.beacon.model;

/**
 * @author Steven Maasch
 *
 */
public class Beacon {

	private Integer id;
	
	private String beaconId;
	
	private String uuid;
	
	private Integer major;
	
	private Integer minor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(String beaconId) {
		this.beaconId = beaconId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}

	public Integer getMinor() {
		return minor;
	}

	public void setMinor(Integer minor) {
		this.minor = minor;
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
		Beacon other = (Beacon) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Beacon [id=" + id + ", beaconId=" + beaconId + ", uuid=" + uuid
				+ ", major=" + major + ", minor=" + minor + "]";
	}

	
	
}
