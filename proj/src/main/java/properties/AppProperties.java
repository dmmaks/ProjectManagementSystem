package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {
    private static Properties props;

    private static Properties getProps() throws IOException {
        if (props == null) {
            props = new Properties();
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "application.properties";

            props.load(new FileInputStream(appConfigPath));
        }
        return props;
    }

    public static String getProperty(String key) {
        try {
            return getProps().getProperty(key);
        } catch (IOException e) {
            return "";
        }
    }
}
