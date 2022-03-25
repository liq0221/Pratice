package com.pinc.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 提供获取inputStream流方法的接口
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
