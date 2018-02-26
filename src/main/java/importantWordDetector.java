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

public class importantWordDetector <C extends ArrayCoreMap> {
    public static void main(String[] args) throws IOException {

        Properties props = new Properties();
        int flag1 = 0, flag2 = 0;
        copyImportantWord find = new copyImportantWord();
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
            System.out.println(read);
            Annotation document = new Annotation(read);
            pipeline.annotate(document);
            String words = "";
            googleSearch search = new googleSearch();
            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                    System.out.println(pos);
                    if(pos.trim().equals("NNP")) {
                        words = words + "_" + word;
                        flag1 = 1;
                    }
                    else{
                        flag1 = 0;
                    }
                    if(flag1 == 0){
                        if(words.equals("")){
                            continue;
                        }
                        else {
                            //search.searchResult(words);
                            find.findMeaning(words);
                            words = "";
                        }
                    }
                }
                if(flag1 == 1){
                    if(words.equals("")){
                        continue;
                    }
                    else {
                        //search.searchResult(words);
                        find.findMeaning(words);
                        words = "";
                    }
                }
            }
        }
    }
}
