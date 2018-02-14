import com.google.common.collect.ImmutableList;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVPojo {
    public static void main (String[]args) throws Exception {
        // getting file data from url
        URL url = new URL("https://zcrm-u-ml.csez.zohocorpin.com:8443/crm/v2/system/file?path=test.txt&zgid=5098730");
        System.out.println(url.toString());
        InputStream is = null;
        is = url.openStream();
        byte[] b = new byte[8];
        String read;
        StringBuilder reader = new StringBuilder();
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(is));
        while (is.read(b) != -1) {
            //System.out.print(new String(b));
            reader.append(bufferReader.readLine());
            reader.append("\n");
        }
        FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
        Path foo = fs.getPath("/fooo");
        Files.createDirectory(foo);
        Path hello = foo.resolve("hello.txt"); // /foo/hello.txt
        Files.write(hello, ImmutableList.of(reader), StandardCharsets.UTF_8);
        InputStream inputStream = Files.newInputStream(hello);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = buffer.readLine()) != null) {
            System.out.println(line);
        }
    }
}
