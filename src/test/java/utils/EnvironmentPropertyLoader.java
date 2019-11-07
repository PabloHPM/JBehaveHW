package utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

@Log4j2
public class EnvironmentPropertyLoader {

    private static Properties properties = new Properties();

    static {
        String propertyFilePath = String.format("configurations/%s.properties", System.getProperty("env"));
        log.info(propertyFilePath);
        final InputStream inputStream =
            Optional.ofNullable(EnvironmentPropertyLoader.class.getClassLoader().getResourceAsStream(propertyFilePath))
                    .orElseThrow(() -> new IllegalStateException(
                        String.format("Properties not be loaded due to file not be specified: %s", propertyFilePath)));
        log.info(inputStream.toString());
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new IllegalStateException(
                String.format("Properties not be loaded due to file not be specified: %s", propertyFilePath));
        }
    }

    public static synchronized String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
