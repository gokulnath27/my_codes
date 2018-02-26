import java.net.MalformedURLException;
import java.net.URL;

public class copyImportantWord  {
    public void findMeaning(String word) throws MalformedURLException {

        String link = "https://en.wikipedia.org/wiki/"+word.trim();
        URL url = new URL(link);
        System.out.println(link);
    }
}
