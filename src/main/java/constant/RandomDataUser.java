package constant;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataUser {
    public static final String RANDOM_EMAIL= RandomStringUtils.randomAlphabetic(4)+"@test.com";
    public static final String RANDOM_PASSWORD = RandomStringUtils.randomNumeric(10);
    public static final String RANDOM_NAME = RandomStringUtils.randomAlphabetic(10);
}
