package cr.ac.ucr.ci1322;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try{
            LexicalAnalyzer analyzer = new LexicalAnalyzer(new FileReader(args[0]));
            analyzer.main(args);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
