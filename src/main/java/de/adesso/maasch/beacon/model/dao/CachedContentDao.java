package de.adesso.maasch.beacon.model.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLException;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;

import de.adesso.maasch.beacon.http.HttpClientFactory;
import de.adesso.maasch.beacon.model.Content;
import de.adesso.maasch.beacon.provider.ConfigProvider;

/**
 * @author Steven Maasch
 *
 */
public class CachedContentDao extends AbstractContentDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CachedContentDao.class);

	@Autowired
	private ConfigProvider configProvider;
	
	public void setConfigProvider(final ConfigProvider configProvider) {
		this.configProvider = configProvider;
	}
	
	private IContentDao contentDao;
	
	public void setContentDao(IContentDao contentDao) {
		this.contentDao = contentDao;
	}
	
	private String contentsFileName;
	
	public void setContentsFileName(final String contentsFileName) {
		this.contentsFileName = contentsFileName;
	}
	
	private List<Content> cachedContentList;
	
	private Date lastModified;
	
	@Override
	public List<Content> getAllContents()
			throws DataAccessResourceFailureException {
		
	    final String host = configProvider.getConfigs().getValueForKey("host");
		final String path = configProvider.getConfigs().getValueForKey("path");
	    final String uri = host + path + contentsFileName;
	    LOGGER.info("Contents-URI: " + uri);
		
	    CloseableHttpClient httpClient;
		try {
			httpClient = HttpClientFactory.getCloseableHttpClientForSsl();
		} catch (SSLException e) {
			throw new DataAccessResourceFailureException("SSL-Exception", e);
		}
		
		
		HttpHead httpHead = new HttpHead(uri);
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(httpHead);
			
			if (!response.containsHeader("Last-Modified")) {
				throw new HttpException("Last-Modified header not present in response.");
			}
	
			final Header responseHeaderLastModified = response.getFirstHeader("Last-Modified");		
			final Date responseLastModified = DateUtils.parseDate(responseHeaderLastModified.getValue());
			
			LOGGER.info("HTTP-response header Last-Modified as timestamp: {}", responseLastModified.getTime());
			LOGGER.info("Cached Last-Modified as timestamp: {}", lastModified != null ? lastModified.getTime() : null);
			
			synchronized (this) {
				if (lastModified == null || lastModified.before(responseLastModified)) {
					LOGGER.info("fetching direct data");
					cachedContentList = contentDao.getAllContents();
					lastModified = responseLastModified;
				}
				
				LOGGER.info("Returning cachedList {}", cachedContentList);
				return cachedContentList;
			}
			
		} catch (IOException | HttpException e) {
			throw new DataAccessResourceFailureException("Failed to retrieve content.", e);
		}
	}
	
}
