

import java.io.File;
import java.net.URL;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.toFile;

public class ConnectionTry2 {
        public static void main(String[] args) throws Exception {
            // FileUtils.toFile(URL url) convert from URL the File.


            // Creates a URL with file protocol and convert it into File object.
            URL url = new URL("https://zcrm-u-ml.csez.zohocorpin.com:8443/crm/v2/system/file?path=test.txt&zgid=5098730");
           // System.out.println(url.toURI().getScheme());
            File file = toFile(url);
            File f = new File(url.getFile());
            System.out.println(f.length());

            String data = readFileToString(file);
            System.out.println("data = " + data);
        }
    }

