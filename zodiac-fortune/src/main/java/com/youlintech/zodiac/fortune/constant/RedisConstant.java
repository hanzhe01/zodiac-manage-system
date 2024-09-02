package com.youlintech.zodiac.fortune.constant;

/**
 * @author oyhz
 */
public class RedisConstant {
    public static final String AUTO_MATERIAL_LIBRARY_TIME_KEY = "constellation:updateMaterialLibrary";
    public static String getMaterialIdKey(Long constellationId) {
        return  "constellation:" + constellationId.toString() + ":material_id";
    }
}
