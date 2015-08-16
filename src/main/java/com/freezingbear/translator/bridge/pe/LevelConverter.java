package com.freezingbear.translator.bridge.pe;

import com.freezingbear.translator.Converter;

/**
 * Created by FreezingBear Team.
 */
public class LevelConverter implements Converter {

    static {
        FreezingPEConveterManager.getInstance().registerConvertor(new LevelConverter(), "level");
    }

}
