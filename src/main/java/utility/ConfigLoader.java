package utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by David on 4/25/2018.
 */
public class ConfigLoader {

    public String getToken(){
        String config = "config.properties";
        Properties prop = new Properties();
        InputStream input = null;
        String token = "";
        try {
            input = getClass().getClassLoader().getResourceAsStream(config);
            URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
            URL urlproperties = new URL(url, config);
            prop.load(urlproperties.openStream());

            token = prop.getProperty("token");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return token;
    }
}
