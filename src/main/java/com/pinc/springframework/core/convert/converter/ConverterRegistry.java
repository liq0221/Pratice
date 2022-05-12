package com.pinc.springframework.core.convert.converter;

/**
 * 类型转化处理注册接口
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(GenericConverter genericConverter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);
}
