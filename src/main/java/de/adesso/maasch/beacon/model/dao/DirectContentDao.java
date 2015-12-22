package de.adesso.maasch.beacon.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.net.ssl.SSLException;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

import de.adesso.maasch.beacon.http.HttpClientFactory;
import de.adesso.maasch.beacon.model.Content;
import de.adesso.maasch.beacon.model.ContentList;
import de.adesso.maasch.beacon.provider.ConfigProvider;

/**
 * @author Steven Maasch
 *
 */
public class DirectContentDao extends AbstractContentDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DirectContentDao.class);
	
	@Autowired
	private ConfigProvider configProvider;
	
	public void setConfigProvider(final ConfigProvider configProvider) {
		this.configProvider = configProvider;
	}
	
	private String contentsFileName;
	
	public void setContentsFileName(final String contentsFileName) {
		this.contentsFileName = contentsFileName;
	}
	
	@Autowired
	private Unmarshaller unmarshaller;
	
	@Override
	public List<Content> getAllContents() throws DataAccessResourceFailureException {
		
	    CloseableHttpClient httpClient;
		try {
			httpClient = HttpClientFactory.getCloseableHttpClientForSsl();
		} catch (SSLException e) {
			throw new DataAccessResourceFailureException("SSL-Exception", e);
		}
		
	    final String host = configProvider.getConfigs().getValueForKey("host");
		final String path = configProvider.getConfigs().getValueForKey("path");
	    final String uri = host + path + contentsFileName;
	    LOGGER.info("Contents-URI: " + uri);
	    
	    HttpGet httpGet = new HttpGet(uri);
		CloseableHttpResponse response = null;
		InputStream inStream = null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			inStream = entity.getContent();	
			ContentList c = (ContentList) unmarshaller.unmarshal(new StreamSource(entity.getContent()));
			System.out.println(c.getContentList());
			return c.getContentList();
		} catch (XmlMappingException|IOException e) {
			throw new DataAccessResourceFailureException("Failed to retrieve content.", e);
		} finally {
			try {
				if (inStream != null && response != null) {
					inStream.close();
					response.close();
				}
			} catch (IOException e) {
				throw new DataAccessResourceFailureException("Failed to close ressources.", e);
			}
		}
	}

	
}
