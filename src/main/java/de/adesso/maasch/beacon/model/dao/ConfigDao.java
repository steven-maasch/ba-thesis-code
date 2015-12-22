package de.adesso.maasch.beacon.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.adesso.maasch.beacon.model.Config;

/**
 * @author Steven Maasch
 *
 */
public class ConfigDao extends JdbcDaoSupport {

	private static final String SQL_SELECT_CONFIGS = "SELECT conf.property, conf.config_value FROM p2761_2742.released_config conf";

	public Config getConfigs() {
		
		return getJdbcTemplate().query(SQL_SELECT_CONFIGS, new ResultSetExtractor<Config>() {
		
			@Override
			public Config extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				
				Map<String, String> map = new HashMap<String, String>();
				
				while (rs.next()) {
					map.put(rs.getString("property"), rs.getString("config_value"));
				}
				
				return new Config(map);
			}
		});
	}
	
}
