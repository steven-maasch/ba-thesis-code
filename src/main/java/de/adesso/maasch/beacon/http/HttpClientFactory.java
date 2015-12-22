package de.adesso.maasch.beacon.http;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLException;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Steven Maasch
 *
 */
public final class HttpClientFactory {

	private HttpClientFactory() {
	}
	
	public static CloseableHttpClient getCloseableHttpClient() {
		return HttpClients.createDefault();
	}
	
	/**
	 * @return A CloseableHttpClient with support for TSL/SSL, that trusts all certificates
	 * @throws SSLException
	 */
	public static CloseableHttpClient getCloseableHttpClientForSsl() throws SSLException {
	    try {
	    	SSLContextBuilder builder = new SSLContextBuilder();
	    	builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
	    	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		    return httpclient;
		} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
			throw new SSLException("Unable to create Ssl context.", e);
		}
	}
	
}
