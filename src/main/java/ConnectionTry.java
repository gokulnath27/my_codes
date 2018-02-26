import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ConnectionTry {
    public static void main(String args[]) throws IOException, URISyntaxException {


        // InputStream input = new URL("http://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf");

        URL url = new URL("https://zcrm-u-ml.csez.zohocorpin.com:8443/crm/v2/system/file?path=test.txt&zgid=5098730");
        //BufferedInputStream input = new BufferedInputStream(url.openStream());
        File f;

        URI uri = url.toURI();
        System.out.println( uri.getScheme() );
        System.out.println(url.toString());
        f = new File(String.valueOf(url.openStream()));
        System.out.println(f.getAbsolutePath());
        System.out.println( f.toURI().toURL() );
        System.out.println(f.length());




       /* URL url1;
        url1 = f.toURL();
         String s1 = f.toURL().toString();
        System.out.println(s1);
        System.out.println(url1.toString());
        String name = url1.toString();

        String s = url1.getPath();
        System.out.println(s);
        System.out.println(name);*/












    }
}