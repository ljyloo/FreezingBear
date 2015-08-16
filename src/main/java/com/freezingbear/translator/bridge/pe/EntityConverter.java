package com.freezingbear.translator.bridge.pe;

import com.freezingbear.translator.Converter;

/**
 * Created by FreezingBear Team.
 */
public class EntityConverter implements Converter {

    static {
        FreezingPEConveterManager.getInstance().registerConvertor(new EntityConverter(), "entity");
    }

}
