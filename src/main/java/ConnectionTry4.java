import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class ConnectionTry4 {
public static void main(String args[]) throws IOException, URISyntaxException {
    URL url = new URL("http://txt2html.sourceforge.net/sample.txt");
    File f = new File(url.getFile());
    System.out.println(f.getAbsolutePath());
    System.out.println(f.getName());
   // System.out.println();

    System.out.println(f.isFile());

    System.out.println(FileUtils.readFileToString(f, "UTF-8"));
}
}
