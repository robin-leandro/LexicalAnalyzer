package cr.ac.ucr.ci1322;
import java_cup.runtime.Symbol;

public class PrintrSymbol {

    private Symbol terminal;
    private NonTerminal nonTerminal;

    public PrintrSymbol(Symbol symbol) {
        this.terminal = symbol;
    }

    public PrintrSymbol(int terminalSym) {
        this.terminal = new Symbol(terminalSym);
    }

    public PrintrSymbol(int terminalSym, Object value) {
        this.terminal = new Symbol(terminalSym,value);
    }

    public PrintrSymbol(NonTerminal nonTerminal) {
        this.nonTerminal = nonTerminal;
    }

    public boolean isTerminal() {
        return this.terminal != null;
    }

    public boolean isNonTerminal() {
        return this.nonTerminal != null;
    }

    public Symbol getTerminal() {
        return terminal;
    }

    public void setTerminal(Symbol terminal) {
        this.terminal = terminal;
    }

    public NonTerminal getNonTerminal() {
        return nonTerminal;
    }

    public void setNonTerminal(NonTerminal nonTerminal) {
        this.nonTerminal = nonTerminal;
    }


}
