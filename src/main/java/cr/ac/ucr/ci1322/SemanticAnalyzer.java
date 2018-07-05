package cr.ac.ucr.ci1322;

import cr.ac.ucr.ci1322.exceptions.SemanticErrorException;

import java.util.LinkedList;

public final class SemanticAnalyzer {
    private static LinkedList<String> alreadyDeclaredVariables;

    private SemanticAnalyzer(ParseTree parseTree) {}

    /**
     * Travels the tree's frist level, analyzing each instruction type (read/print) appropriately
     */
    public static LinkedList<String> analyze(ParseTree parseTree) {
        alreadyDeclaredVariables = new LinkedList<>();
        for (Node currentNode : parseTree.getRoot().getChildren()) {
            if (currentNode.isNonTerminal()) {

                if (currentNode.getPrintrSymbol().getNonTerminal() == NonTerminal.N_PRINT_INSTRUCTION)
                    analyzePrintInstruction(currentNode);

                else if (currentNode.getPrintrSymbol().getNonTerminal() == NonTerminal.N_READ_INSTRUCTION)
                    analyzeReadInstruction(currentNode);
            }
        }
        return  alreadyDeclaredVariables;
    }

    /**
     * Check that the variable to be printed in this instruction has been declared
     * @param printNode the node that holds all the metadata related to a print instruction
     */
    private static void analyzePrintInstruction(Node printNode) {
        String variableName = (String)printNode.getChildren().getFirst().getPrintrSymbol().getTerminal().value;
        if(!alreadyDeclaredVariables.contains(variableName))
            throw new SemanticErrorException("undeclared variable "+variableName);
    }

    /**
     * Check that the number of declared variables matches the number specified in the parenthesis
     * @param readNode the node that holds all the metadata related to a read instruction
     */
    private static void analyzeReadInstruction(Node readNode) {
        int numberInParenthesis = (Integer)readNode.getChildren().get(1).getPrintrSymbol().getTerminal().value;
        if(numberInParenthesis < 1 || numberInParenthesis > 6)
            throw new SemanticErrorException("The read instruction may only receive numbers between 1 and 5");

        LinkedList<String> variablesToBeDeclared = new LinkedList<>();
        for(Node declarationNode:readNode.getChildren().getFirst().getChildren())
                variablesToBeDeclared.add((String)declarationNode.getPrintrSymbol().getTerminal().value);

        int declaredVariables = variablesToBeDeclared.size();

        // If they don't match, throw an exception
        if(declaredVariables != numberInParenthesis) {
            String errorString = "amount of declared variables does not match amount to be read"
                    +"\nAmount of variables to be read: "+numberInParenthesis
                    +"\nAmount of declared variables: "+declaredVariables;
            for(String variableToBeDeclared:variablesToBeDeclared)
                errorString += "\n  "+variableToBeDeclared;
            throw new SemanticErrorException(errorString);
        }

        // If they match, add the declared variables to the list
        alreadyDeclaredVariables.addAll(variablesToBeDeclared);
    }


}
