package de.adesso.maasch.beacon.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.adesso.maasch.beacon.model.Config;
import de.adesso.maasch.beacon.model.dao.ConfigDao;

/**
 * @author Steven Maasch
 *
 */
@Component
public class ConfigProvider implements IConfigProvider {

	@Autowired
	private ConfigDao configDao;

	@Override
	public Config getConfigs() {
		return configDao.getConfigs();
	}

}
