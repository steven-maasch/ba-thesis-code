package de.adesso.maasch.beacon.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import de.adesso.maasch.beacon.model.Beacon;

/**
 * @author Steven Maasch
 *
 */
public class BeaconDao extends NamedParameterJdbcDaoSupport {

	private static final String SQL_SELECT_BEACON_BY_ID = "SELECT * "
			+ "FROM p2761_2742.released_beacon b "
			+ "WHERE b.fs_id = :id";
	
	private static final String SQL_SELECT_BEACON_BY_BEACON_PROPS = "SELECT * "
			+ "FROM p2761_2742.released_beacon b "
			+ "WHERE b.uuid = :uuid "
			+ "AND b.major = :major "
			+ "AND b.minor = :minor";
	
	public Beacon findById(Integer id) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		return getNamedParameterJdbcTemplate().queryForObject(SQL_SELECT_BEACON_BY_ID, params, new RowMapper<Beacon>() {

			@Override
			public Beacon mapRow(ResultSet rs, int rowNum) throws SQLException {
				final Beacon beacon = new Beacon();
				beacon.setId(rs.getInt("fs_id"));
				beacon.setBeaconId(rs.getString("beacon_id"));
				beacon.setUuid(rs.getString("uuid"));
				beacon.setMajor(rs.getInt("major"));
				beacon.setMinor(rs.getInt("minor"));
				return beacon;
			}
			
		});
		
	}
	
	public Beacon findByUuidAndMajorAndMinor(String uuid, Integer major, Integer minor) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("uuid", uuid);
		params.put("major", major);
		params.put("minor", minor);
		
		return getNamedParameterJdbcTemplate().queryForObject(SQL_SELECT_BEACON_BY_BEACON_PROPS, params, new RowMapper<Beacon>() {

			@Override
			public Beacon mapRow(ResultSet rs, int rowNum) throws SQLException {
				final Beacon beacon = new Beacon();
				beacon.setId(rs.getInt("fs_id"));
				beacon.setBeaconId(rs.getString("beacon_id"));
				beacon.setUuid(rs.getString("uuid"));
				beacon.setMajor(rs.getInt("major"));
				beacon.setMinor(rs.getInt("minor"));
				return beacon;
			}
			
		});
	}
	
}
