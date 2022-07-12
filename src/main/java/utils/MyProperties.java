package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyProperties {
    private static final String PATH = "src/main/resources/bot.properties";
    private static final Properties PROPERTIES = getProperty();

    private MyProperties() {
    }

    public static String getTelegramBotToken() {
        return PROPERTIES.getProperty("bot.token");
    }

    public static String getTelegramBotName() {
        return PROPERTIES.getProperty("bot.name");
    }

    private static Properties getProperty() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            //#TODO
        }
        return properties;
    }
}
