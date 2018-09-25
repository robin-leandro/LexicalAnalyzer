package cr.ac.ucr.ci1322;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        FileReader reader = null;
        try{
            reader = new FileReader(".src/main/resources/ejemplo_documento.txt");
        }catch(FileNotFoundException e){
            e.printStackTrace();

        }
        LexicalAnalyzer analyzer = new LexicalAnalyzer(reader);
        analyzer.main(args);
    }
}
