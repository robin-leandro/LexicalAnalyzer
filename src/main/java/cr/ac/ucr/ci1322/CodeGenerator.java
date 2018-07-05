package cr.ac.ucr.ci1322;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

/**
 * After all parsing and analysis is complete, parses the tree to generate executable MIPS code.
 * Follows the following steps:
 *      - Write the .data section
 *      - allocate in the .data section a word for every variable declared in the source code
 *      - Write the .text section, and in there a .main
 *      - Parse the second level of the tree, using the templates to translate the read/print instructions
 *      - When the tree has been parsed, write the code to call the exit syscall
 */
public class CodeGenerator {
    // Templates to be used to facilitate code generation

    // used in other templates
    private static final String syscall = "syscall";

    // Writes the .data section
    private static final String data = "   .data";

    // Used to allocate space for an integer declared in the source code
    private static final String variableDeclarationTemplate = ": .word 1";

    // Writes the .text section, with a .main
    private static final String text = "   .text";
    private static final String main = "main:";

    // Translates printr's print function
    private static final String printTemplateLine1 = "lw $a0, ";
    private static final String printTemplateLine2 = "li $v0, 1";

    // Translates printr's read function
    private static final String readTemplateLine1 = "li $v0, 5";
    private static final String readTemplateLine2 = "sw $v0, ";

    // Writes the exit syscall
    private static final String exitStatement = "li  $v0, 10";


    private static LinkedList<String> mipsLines = new LinkedList<>();


    public static void generateCode(ParseTree parseTree, LinkedList<String> declaredVariables) throws IOException {

        // start by writing the data section
        mipsLines.add(data);

        // in the data section we write every variable to be used
        writeDeclaredVariables(declaredVariables);

        mipsLines.add(text);
        mipsLines.add(main);

        // parse the tree using the temlates to translate print/read instructions
        for(Node instructionNode:parseTree.getRoot().getChildren()){
            if(instructionNode.getPrintrSymbol().getNonTerminal() == NonTerminal.N_READ_INSTRUCTION)
                writeReadStatement(instructionNode);
            else if(instructionNode.getPrintrSymbol().getNonTerminal() == NonTerminal.N_PRINT_INSTRUCTION)
                writePrintStatement(instructionNode);
        }

        // write the syscall to end the program
        writeExitStatement();

        // write the file
        Files.write(Paths.get("printrOutput.s"), mipsLines, StandardCharsets.UTF_8,
            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }

    /**
     * Uses the variable declaration template to write all the variables that will be used in the program
     * @param declaredVariables
     */
    private static void writeDeclaredVariables(LinkedList<String> declaredVariables){
        for(String variable:declaredVariables)
            mipsLines.add(variable+variableDeclarationTemplate);
    }

    private static void writeReadStatement(Node readNode) {
        for(Node variableNode: readNode.getChildren().getFirst().getChildren()){
            mipsLines.add(readTemplateLine1);
            mipsLines.add(syscall);
            mipsLines.add(readTemplateLine2+variableNode.getPrintrSymbol().getTerminal().value);
        }
    }

    private static void writePrintStatement(Node printNode){
        mipsLines.add(printTemplateLine1+printNode.getChildren().getFirst().getPrintrSymbol().getTerminal().value);
        mipsLines.add(printTemplateLine2);
        mipsLines.add(syscall);
    }

    private static void writeExitStatement() {
        mipsLines.add(exitStatement);
        mipsLines.add(syscall);
    }

}
