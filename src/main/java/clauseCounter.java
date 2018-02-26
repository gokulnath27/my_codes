import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.*;

import java.io.*;
import java.util.List;

class clauseCounter {

    public static void main(String[] args) throws Exception {
        LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
        String path = "/Users/gokul-pt1831/Downloads/sample.txt";
        try {
            demoAPI(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void demoAPI(LexicalizedParser lp) throws Exception {

        Tree parse;
        File file = new File("/Users/gokul-pt1831/Downloads/Football.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null){
            builder.append(line);

        }
        TokenizerFactory<CoreLabel> tokenizerFactory =
                PTBTokenizer.factory(new CoreLabelTokenFactory(), "");

        Tokenizer<CoreLabel> tokenizer = tokenizerFactory.getTokenizer(new StringReader(builder.toString()));
        List<CoreLabel> rawWords2 = tokenizer.tokenize();
        parse = lp.apply(rawWords2);
        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        TreePrint tp = new TreePrint("penn");
        PrintWriter pw = new PrintWriter(builder.toString());
        System.out.println(parse.pennString());
        String s = parse.pennString();
        String substring1 = "(SBAR";
        String substring2 = "(S";
        int count1 = 0, count2 = 0;
        int idx = 0;
        while ((idx = s.indexOf(substring1, idx)) != -1) {
            idx++;
            count1++;
        }
        idx = 0;
        while ((idx = s.indexOf(substring2, idx)) != -1) {
            idx++;
            count2++;
        }
        System.out.println("Total number of clauses:" + count2);
        System.out.println("No of SBAR(subordinate clause):"+count1);
        System.out.println("No of S(simple declarative clause):"+ (count2-count1));
    }
}