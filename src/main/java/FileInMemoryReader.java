import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class FileInMemoryReader {
    public static void main(String[] args) throws Exception {
        URL url = new URL("file://~/Desktop/a.txt");
        File file = Paths.get(url.toURI()).toFile();

        System.out.println(file.length());
    }
}
