import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.*;

import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;
import java.util.Set;

class TaggerDemo2 {

    public static void main(String[] args) throws Exception {
        LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
        String path = "/Users/gokul-pt1831/Downloads/sample.txt";
        //demoDP(lp, path);

        try {
            demoAPI(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void demoDP(LexicalizedParser lp, String filename) {

        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();

        for (List<HasWord> sentence : new DocumentPreprocessor(filename)) {
            Tree parse = lp.apply(sentence);
            // parse.pennPrint();

            System.out.println();

            GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
            Collection tdl = gs.typedDependenciesCCprocessed();
            // System.out.println(tdl);
            System.out.println();
        }
    }

    public static void demoAPI(LexicalizedParser lp) throws Exception {
        // This option shows parsing a list of correctly tokenized words
        String[] sent = {"This", "is", "a", "test", "sentence", "."};
        List<CoreLabel> rawWords = SentenceUtils.toCoreLabelList(sent);
        Tree parse = lp.apply(rawWords);
        //parse.pennPrint();
        //System.out.println();

        // This option shows loading and using an explicit tokenizer
        String sent2 = "abc cite the fact that chemical additive be ban in many country and feel they may be ban in this state too. " +
                "She cried because her seashell was broken." +
                " Whoever ate the last piece of pie owes me!." +
                " The bag that someone left on the bus belongs to Mrs. Smith.";
        TokenizerFactory<CoreLabel> tokenizerFactory =
                PTBTokenizer.factory(new CoreLabelTokenFactory(), "");

        Tokenizer<CoreLabel> tokenizer = tokenizerFactory.getTokenizer(new StringReader(sent2));
        List<CoreLabel> rawWords2 = tokenizer.tokenize();
        parse = lp.apply(rawWords2);

        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
        //System.out.println(tdl.get(1).toString());


        //System.out.println(tdl);

        System.out.println();

        // You can also use a TreePrint object to print trees and dependencies

        TreePrint tp = new TreePrint("penn");
        Set<Constituent> constituents = parse.constituents();
        //System.out.println(constituents.toString());
        //tp.printTree(parse);
        PrintWriter pw = new PrintWriter(sent2);
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
        //System.out.println(count1);



        idx = 0;

        while ((idx = s.indexOf(substring2, idx)) != -1) {
            idx++;
            count2++;
        }
        //System.out.println(count2);


        System.out.println("No of SBAR(subordinate clause):" + count1);
        System.out.println("No of S(simple declarative clause):" + (count2-count1));
        System.out.println("total number of clauses:" + count2);


    }
}