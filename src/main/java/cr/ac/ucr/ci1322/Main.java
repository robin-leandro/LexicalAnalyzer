package cr.ac.ucr.ci1322;

import cr.ac.ucr.ci1322.exceptions.LexicalErrorException;
import cr.ac.ucr.ci1322.exceptions.SemanticErrorException;
import cr.ac.ucr.ci1322.exceptions.SyntacticErrorException;
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

                CodeGenerator.generateCode(syntacticAnalyzer.getParseTree(),SemanticAnalyzer.analyze(syntacticAnalyzer.getParseTree()));

                System.out.println("Done compiling! Your executable MIPS file is called printrOutput.s\nHere's a tree representation of your code:\n"+syntacticAnalyzer.getParseTree().toString());


            } catch(FileNotFoundException e) {
                System.out.println("Could not find "+args[0]);
            } catch(LexicalErrorException e) {
                e.printStackTrace();
            } catch(SyntacticErrorException e) {
                e.printStackTrace();
            } catch (SemanticErrorException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }

        }
    }
}
