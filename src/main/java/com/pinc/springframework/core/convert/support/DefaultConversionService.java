package com.pinc.springframework.core.convert.support;

import com.pinc.springframework.core.convert.converter.ConverterRegistry;

public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类型的转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());

    }
}
