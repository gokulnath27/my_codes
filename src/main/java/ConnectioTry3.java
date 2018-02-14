import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectioTry3 {

    public static void main(String args[]){

        URL url = null;
        InputStream is = null;
        try {
            url = new URL("https://zcrm-u-ml.csez.zohocorpin.com:8443/crm/v2/system/file?path=test.txt&zgid=5098730");
            System.out.println(url.toString());
            is = url.openStream();
            byte[] b = new byte[8];
            while(is.read(b) != -1){
                System.out.print(new String(b));
            }
        }  catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}