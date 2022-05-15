package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class MyProperties {
    private static final String PATH = "src/main/resources/bot.properties";
    private static final Properties PROPERTIES = getProperty();

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
        } catch (Exception e) {
            //#TODO
            System.out.println("File not found");
        }
        return properties;
    }
}
