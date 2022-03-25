package com.pinc.springframework.core.io;

import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 通过http方式读取云服务的文件
 */
public class UrlPathResource implements Resource {

    private URL url;

    public UrlPathResource(URL url) {
        Assert.notNull(url, "url must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection connection = this.url.openConnection();
        try {
            return connection.getInputStream();
        } catch (IOException e) {
            if (connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).disconnect();
            }
           throw e;
        }
    }
}
