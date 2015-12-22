package de.adesso.maasch.beacon.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.adesso.maasch.beacon.provider.IBeaconPositionProvider;

/**
 * @author Steven Maasch
 *
 */
public class Main {
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		BeanFactory factory = context;
		
		LOGGER.info("Getting Content-Provider.");
		@SuppressWarnings("unused")
		IBeaconPositionProvider beaconPositionProvider = factory.getBean(IBeaconPositionProvider.class);
		LOGGER.info("Have ContentProvider.");
	
	}
} 
