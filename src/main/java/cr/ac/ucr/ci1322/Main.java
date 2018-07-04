package cr.ac.ucr.ci1322;

import java_cup.runtime.ComplexSymbolFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Please include a single argument that " +
                    "contains a path to a .txt source code file.");
        } else {
            try {
                FileReader sourceCode = new FileReader(args[0]);
                ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
                LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(sourceCode, symbolFactory);
                SyntacticAnalyzer syntacticAnalyzer = new SyntacticAnalyzer(lexicalAnalyzer, symbolFactory);
                syntacticAnalyzer.parse();
            } catch(FileNotFoundException e) {
                System.out.println("Could not find "+args[0]);
            } catch(Exception e) {
                e.printStackTrace();
            }

        }
    }
}
