package cr.ac.ucr.ci1322;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.Symbol;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ErrorReporter {
    private static Map<Integer, String> sourceCode = new TreeMap<>();
    private static StringBuilder errorMessage = new StringBuilder();

    public static void addSourceLine(Integer lineNumber, String lineString) {
        sourceCode.put(lineNumber, lineString);
    }

    public static void lexicalError(String symbol, int line, int column) {
        lexicalError("Caracter no reconocido", symbol, line, column, 1);
    }

    public static void lexicalError(String message, String symbol, int line, int column, int length) {
        errorMessage.append("=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        errorMessage.append("Error Lexico\n");
        errorMessage.append(message).append("\n");
        errorMessage.append("Caracter(es): ").append(symbol).append("\n");

        addLineError(line, column, 1);

        errorMessage.append("++++++ Error Recuperable ++++++\n");
        errorMessage.append("=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        System.out.println(errorMessage);
        errorMessage.setLength(0);
    }


    public static void syntaxError(ComplexSymbol complexSymbol, List<Integer> expectedIds) {
        syntaxError(complexSymbol, expectedIds, "Symbolo inesperado");
    }

    public static void syntaxError(ComplexSymbol complexSymbol, List<Integer> expectedIds, String message) {
        // Complex symbol info
        int line = complexSymbol.xleft.getLine();
        int column = complexSymbol.xleft.getColumn();
        int length = complexSymbol.xleft.getColumn() - complexSymbol.xright.getColumn();

        errorMessage.append("=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        errorMessage.append("Error Sintactico\n");
        errorMessage.append(message).append("\n");
        errorMessage.append("Simbolo: ");
        errorMessage.append(getIdName(complexSymbol.sym)).append("\n");

        errorMessage.append("Linea:   ").append(line).append("\n");
        errorMessage.append("Columna: ").append(column).append("\n");

        addLineError(line, column, length);

        addExpectedIds(expectedIds);

        errorMessage.append("++++++Error Irrecuperable++++++\n");
        errorMessage.append("=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        System.out.println(errorMessage);
        errorMessage.setLength(0);
    }

    public static void semanticError(Symbol symbol) {
        semanticError("Variable no definida", symbol);
    }

    public static void semanticError(String message, Symbol symbol) {
        errorMessage.append("=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        errorMessage.append("Error Semantico\n");
        errorMessage.append(message).append("\n");

        errorMessage.append("Identificador: ");
        errorMessage.append(symbol.value.toString()).append("\n");

        if (symbol instanceof ComplexSymbol) {
            addLineError((ComplexSymbol) symbol);
        }

        //errorMessage.append("++++++Error Irrecuperable++++++\n");
        errorMessage.append("=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        System.out.println(errorMessage);
        errorMessage.setLength(0);
    }

    private static void addLineError(ComplexSymbol complexSymbol) {
        int line = complexSymbol.xleft.getLine();
        int column = complexSymbol.xleft.getColumn();
        int length = complexSymbol.xright.getColumn() - complexSymbol.xleft.getColumn();

        addLineError(line, column, length);
    }

    private static void addLineError(int line, int column, int length) {
        errorMessage.append("************************************\n");

        // Erroneous line
        String lineNumber = line + " ";
        errorMessage.append(lineNumber);
        errorMessage.append(sourceCode.get(line).replace('\t', ' ')).append("\n");
        int errorPosition = column + lineNumber.length();
        // --------------

        // Pointer to erroneous symbol
        for(int i = 0; i < errorPosition; ++i) {
            errorMessage.append(" ");
        }
        errorMessage.append("^");
        for(int i = 0; i < length - 1; ++i) {
            errorMessage.append("~");
        }
        // --------------

        errorMessage.append("\n************************************\n");
    }

    private static void addExpectedIds(List<Integer> expectedIds) {
        errorMessage.append("Los simbolos esperados son: ");
        for (Integer id : expectedIds) {
            if (id == Terminal.error) continue;
            errorMessage.append(getIdName(id));
            errorMessage.append(", ");
        }
        errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
        errorMessage.append("\n");
    }

    public static String getIdName(int id) {
        switch (id) {
            case Terminal.T_IDENTIFIER:         return "identifier";
            case Terminal.T_INTEGER:       return "integer";
            default: {
                String terminalName = Terminal.terminalNames[id];
                terminalName = terminalName.charAt(0) + terminalName.substring(1).toLowerCase();
                return terminalName;
            }
        }
    }

}
