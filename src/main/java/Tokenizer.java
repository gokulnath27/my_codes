import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.ArrayCoreMap;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer<C extends ArrayCoreMap> {

    public static void main(String[] args) throws IOException {
        int count = 0;
        float i, syllables = 0;
        float averageCount = 0;
        syllableCounter counter = new syllableCounter();
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        props.setProperty("ssplit.isOneSentence", "true");
        props.setProperty("tokenize.language", "en");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        String path = "/Users/gokul-pt1831/Downloads/sample.txt";
        File file = new File(path);
        FileReader reader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(reader);
        while(buffer.read()!= -1){
            String read = buffer.readLine();
            i = counter.syllableCount(read);
            syllables = syllables + i;

            //System.out.println(read);
            Annotation document = new Annotation(read);
            pipeline.annotate(document);
            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    //System.out.println(sentence.toString());
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                    //System.out.println(word);
                    //System.out.println(pos);
                    /*if(pos.trim().equals("CC")){
                        continue;
                    }
                    else {
                        count++;
                    }*/
                    Pattern regex = Pattern.compile("[^A-Za-z0-9]");
                    Matcher m = regex.matcher(word);
                    if(m.find()){
                        continue;

                    }
                    else
                    count++;
                    //System.out.println(count);

                }

            }
            System.out.println("word count:" + count);
            System.out.println("syllable count:" + syllables);
            averageCount = syllables/count;
            System.out.println("average:" + averageCount);
        }
    }
}
