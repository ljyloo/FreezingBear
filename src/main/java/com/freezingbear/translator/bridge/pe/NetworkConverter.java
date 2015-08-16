package com.freezingbear.translator.bridge.pe;

import com.freezingbear.translator.Converter;

/**
 * Created by FreezingBear Team.
 */
public class NetworkConverter implements Converter {

    static {
        FreezingPEConveterManager.getInstance().registerConvertor(new NetworkConverter(), "network");
    }

}
