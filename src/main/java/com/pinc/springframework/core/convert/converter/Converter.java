package com.pinc.springframework.core.convert.converter;

/**
 * 类型处理转换接口
 * @param <S>
 * @param <T>
 */
public interface Converter<S, T> {

    T convert(S source);
}
