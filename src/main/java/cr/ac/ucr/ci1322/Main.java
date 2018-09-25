package cr.ac.ucr.ci1322;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try{
            if(args[1].equals("consulta")) {
                QueryLexicalAnalyzer analyzer = new QueryLexicalAnalyzer(new FileReader(args[0]));
                analyzer.main(args);
            }else{
                DocumentLexicalAnalyzer analyzer = new DocumentLexicalAnalyzer(new FileReader(args[0]));
                analyzer.main(args);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
