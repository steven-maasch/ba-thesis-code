package de.adesso.maasch.beacon.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import de.adesso.maasch.beacon.model.Facility;

/**
 * @author Steven Maasch
 *
 */
public class FacilityDao extends NamedParameterJdbcDaoSupport {

	private static final String SQL_SELECT_FACILTY_BY_ID = "SELECT * "
			+ "FROM p2761_2742.released_facility f "
			+ "WHERE f.fs_id = :id";
	
	public Facility findById(final Integer id) {
		
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		return getNamedParameterJdbcTemplate().queryForObject(SQL_SELECT_FACILTY_BY_ID, params, new RowMapper<Facility>() {

			@Override
			public Facility mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				final Facility facility = new Facility();
				facility.setId(rs.getInt("fs_id"));
				facility.setFirmName(rs.getString("firm_name"));
				facility.setFirmName2(rs.getString("firm_name2"));
				facility.setStreet(rs.getString("street"));
				facility.setPostalCode(rs.getString("postal_code"));
				facility.setCity(rs.getString("city"));
				return facility;
			}
		});
	}
	
}
