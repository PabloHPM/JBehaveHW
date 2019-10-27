package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Optional.ofNullable;

public class EnvironmentPropertyLoader {

    private static Properties properties = new Properties();

    private EnvironmentPropertyLoader() {
    }

    static {
        String pathToPropertyFile = String.format("configurations/%s.properties", System.getProperty("env"));
        final InputStream inputStream = ofNullable(EnvironmentPropertyLoader.class.getClassLoader().getResourceAsStream(pathToPropertyFile))
                .orElseThrow(
                        () -> new IllegalStateException("Unable to load properties - file might not be specified: ".concat(pathToPropertyFile)));
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {

            throw new IllegalStateException("Unable to load properties from resource: ".concat(pathToPropertyFile));

        }

    }

    public static synchronized String getProperty(String propertyName) {
        return System.getProperty(propertyName, properties.getProperty(propertyName));
    }
}
