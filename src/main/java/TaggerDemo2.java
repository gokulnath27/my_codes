import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.*;
import edu.stanford.nlp.trees.*;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;

class TaggerDemo2 {

    public static void main(String[] args) throws Exception {
        LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
        String path = "/Users/gokul-pt1831/Downloads/sample.txt";
            demoDP(lp, path);

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
       // parse.pennPrint();
       // System.out.println();

        // This option shows loading and using an explicit tokenizer
        String sent2 = "This is another test sentence.";
        TokenizerFactory<CoreLabel> tokenizerFactory =
                PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
        Tokenizer<CoreLabel> tok =
                tokenizerFactory.getTokenizer(new StringReader(sent2));
        List<CoreLabel> rawWords2 = tok.tokenize();
        parse = lp.apply(rawWords2);

        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);


        List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();


        System.out.println(tdl);

        System.out.println();

        // You can also use a TreePrint object to print trees and dependencies
        TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
        tp.printTree(parse);


    }
}

