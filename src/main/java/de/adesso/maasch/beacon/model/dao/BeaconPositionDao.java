package de.adesso.maasch.beacon.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import de.adesso.maasch.beacon.model.Beacon;
import de.adesso.maasch.beacon.model.BeaconPosition;
import de.adesso.maasch.beacon.model.Content;
import de.adesso.maasch.beacon.model.Facility;

/**
 * @author Steven Maasch
 *
 */
public class BeaconPositionDao extends NamedParameterJdbcDaoSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeaconPositionDao.class);
	
	@Autowired
	private BeaconDao beaconDao;
	
	@Autowired
	private FacilityDao facilityDao;
	
	private IContentDao contentDao;
	
	public void setContentDao(IContentDao contentDao) {
		this.contentDao = contentDao;
	}
	
	private static final String SQL_SELECT_BEACON_POSITION_BY_BEACON = "SELECT * "
			+ "FROM p2761_2742.released_beacon_position bp "
			+ "WHERE bp.beac_fs_id3 = :id";
	
	private static final String SQL_SELECT_BEACON_POSITION_BY_ID = "SELECT * "
			+ "FROM p2761_2742.released_beacon_position bp "
			+ "WHERE bp.fs_id = :id";
	
	private static final String SQL_SELECT_CONTENT_IDS_BY_BEACON_POSITION_ID = "SELECT cont_fs_id " +
			"FROM p2761_2742.released_rt_beacon_position_content_beacon_positionlist " + 
			"WHERE beac_fs_id = :id";
	
	public BeaconPosition findByBeacon(final Beacon beacon) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", beacon.getId());
		
		return getNamedParameterJdbcTemplate().queryForObject(SQL_SELECT_BEACON_POSITION_BY_BEACON, params, new RowMapper<BeaconPosition>() {

			@Override
			public BeaconPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
				final BeaconPosition beaconPosition = new BeaconPosition();
				beaconPosition.setId(rs.getInt("fs_id"));
				beaconPosition.setName(rs.getString("name"));
				
				final Beacon beacon = beaconDao.findById(rs.getInt("beac_fs_id3"));
				final Facility facility = facilityDao.findById(rs.getInt("faci_fs_id1"));
				
				final Map<String, Object> params = new HashMap<String, Object>();
				params.put("id", beaconPosition.getId());
				
				List<Integer> contentsIds = null;
				
				try {
				contentsIds = getNamedParameterJdbcTemplate().query(SQL_SELECT_CONTENT_IDS_BY_BEACON_POSITION_ID, 
						params, new RowMapper<Integer>() {

							@Override
							public Integer mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return rs.getInt("cont_fs_id");
							}
						
				
				});
				} catch (EmptyResultDataAccessException e) {
					LOGGER.info("No contents for beacon position", e);
				}
				
				final List<Content> contents = contentDao.getContentsByIds(contentsIds);
				beaconPosition.setBeacon(beacon);
				beaconPosition.setFacility(facility);
				beaconPosition.setContents(contents);
				return beaconPosition;
			}
		
		});
		
	}
	
	public BeaconPosition findById(final Integer id) {
		
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		return getNamedParameterJdbcTemplate().queryForObject(SQL_SELECT_BEACON_POSITION_BY_ID, params, new RowMapper<BeaconPosition>() {

			@Override
			public BeaconPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
				final BeaconPosition beaconPosition = new BeaconPosition();
				beaconPosition.setId(rs.getInt("fs_id"));
				beaconPosition.setName(rs.getString("name"));
				
				final Beacon beacon = beaconDao.findById(rs.getInt("beac_fs_id3"));
				final Facility facility = facilityDao.findById(rs.getInt("faci_fs_id1"));
				
				beaconPosition.setBeacon(beacon);
				beaconPosition.setFacility(facility);
				
				return beaconPosition;
			}
		
		});
	}
	
}
