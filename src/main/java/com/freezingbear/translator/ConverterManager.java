package com.freezingbear.translator;

/**
 * Created by FreezingBear Team
 */
public abstract class ConverterManager {

    public abstract void registerConvertor(Converter converter, String type);

    public abstract Converter getConvertor(String type);

}
