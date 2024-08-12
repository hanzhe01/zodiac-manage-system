package com.youlintech.zodiac.chatai.enums;

public enum ZodiacEnum {
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

    ZodiacEnum(Integer code, String name) {
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
        return null; // 或者可以抛出异常，具体取决于业务需求
    }
    /**
     * 根据给定的星座名称获取对应的星座id
     *
     * @param name 星座名称
     * @return 对应的星座名称，如果未找到则返回null
     */
    public static Integer getIdByName(String name) {
        for (ZodiacEnum sign : values()) {
            if (sign.getName().equals(name)) {
                return sign.getCode();
            }
        }
        return null; // 或者可以抛出异常，具体取决于业务需求
    }
}
