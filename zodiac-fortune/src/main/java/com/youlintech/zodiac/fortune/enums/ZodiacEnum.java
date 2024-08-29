package com.youlintech.zodiac.fortune.enums;
/**
 * 枚举类表示十二星座。
 * @author oyhz
 *
 * @date 2024-07-17
 */
public enum ZodiacEnum {
    /**
     * 枚举类表示十二星座。
     * @author oyhz
     *
     * @date 2024-07-17
     */
    ARIES(1, "Aries"),
    TAURUS(2, "Taurus"),
    GEMINI(3, "Gemini"),
    CANCER(4, "Cancer"),
    LEO(5, "Leo"),
    VIRGO(6, "Virgo"),
    LIBRA(7, "Libra"),
    SCORPIO(8, "Scorpio"),
    SAGITTARIUS(9, "Sagittarius"),
    CAPRICORN(10, "Capricorn"),
    AQUARIUS(11, "Aquarius"),
    PISCES(12, "Pisces");

    private final Integer code;
    private final String name;

    ZodiacEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    /**
     * 根据给定的code值获取对应的星座名称
     *
     * @param code 星座的代码值
     * @return 对应的星座名称，如果未找到则返回null
     */
    public static String getNameByCode(int code) {
        for (ZodiacEnum sign : values()) {
            if (sign.getCode() == code) {
                return sign.getName();
            }
        }
        // 或者可以抛出异常，具体取决于业务需求
        return null;
    }
}
