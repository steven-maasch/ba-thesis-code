package de.adesso.maasch.beacon.model;

/**
 * @author Steven Maasch
 *
 */
public class Facility {
	
	private Integer id;
	
	private String firmName;
	
	private String firmName2;
	
	private String street;
	
	private String postalCode;
	
	private String city;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getFirmName2() {
		return firmName2;
	}

	public void setFirmName2(String firnName2) {
		this.firmName2 = firnName2;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
		Facility other = (Facility) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Facility [id=" + id + ", firmName=" + firmName + ", firnName2="
				+ firmName2 + ", street=" + street + ", postalCode="
				+ postalCode + ", city=" + city + "]";
	}
	
}
