package com.freezingbear.translator.bridge.pe;

import com.freezingbear.translator.Converter;
import com.freezingbear.translator.ConverterManager;

import java.util.HashMap;

/**
 * Created by FreezingBear Team.
 */
public class FreezingPEConveterManager extends ConverterManager {

    private static FreezingPEConveterManager instance;

    public static FreezingPEConveterManager getInstance(){
        if(instance == null){
            instance = new FreezingPEConveterManager();
        }
        return instance;
    }

    public HashMap<String, Converter> converters = new HashMap<String, Converter>();

    @Override
    public void registerConvertor(Converter converter, String type) {
        converters.put(type, converter);
    }

    @Override
    public Converter getConvertor(String type) {
        return converters.get(type);
    }
}
