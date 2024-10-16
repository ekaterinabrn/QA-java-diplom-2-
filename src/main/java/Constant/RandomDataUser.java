package Constant;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataUser {
    public static String RANDOM_EMAIL= RandomStringUtils.randomAlphabetic(4)+"@test.com";
    public static String RANDOM_PASSWORD = RandomStringUtils.randomNumeric(10);
    public static String RANDOM_NAME = RandomStringUtils.randomAlphabetic(10);
}
