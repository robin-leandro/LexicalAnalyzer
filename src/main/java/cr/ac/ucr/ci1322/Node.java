package cr.ac.ucr.ci1322;

import cr.ac.ucr.ci1322.exceptions.SyntacticErrorException;
import java_cup.runtime.Symbol;

import java.util.LinkedList;

public class Node {
    private LinkedList<Node> children;

    private PrintrSymbol printrSymbol;

    public Node() {
        this.printrSymbol = null;
        children = new LinkedList<>();
    }

    public Node(PrintrSymbol printrSymbol) {
        this.printrSymbol = printrSymbol;
        children = new LinkedList<>();
    }

    public Node(Symbol symbol) {
        this.printrSymbol = new PrintrSymbol(symbol);
        children = new LinkedList<>();
    }

    public Node(int terminalSym, Object value) {
        this.printrSymbol = new PrintrSymbol(terminalSym,value);
        children = new LinkedList<>();
    }

    public Node(int terminalSym) {
        this.printrSymbol = new PrintrSymbol(terminalSym);
        children = new LinkedList<>();
    }


    public Node(NonTerminal nonTerminal) {
        this.printrSymbol = new PrintrSymbol(nonTerminal);
        children = new LinkedList<>();
    }

    public void setChildren(LinkedList<Node> children) {
        this.children = children;
    }

    public LinkedList<Node> getChildren() {
        return children;
    }

    public void addChild(Node node) {
        this.children.add(node);
    }

    public Node getChild(int n) {
        return children.get(n);
    }

    public void setPrintrSymbol(PrintrSymbol type) {
        this.printrSymbol = type;
    }

    public PrintrSymbol getPrintrSymbol() {
        return printrSymbol;
    }

    public boolean isTerminal(){
        return this.printrSymbol.isTerminal();
    }

    public boolean isNonTerminal(){
        return this.printrSymbol.isNonTerminal();
    }

    @Override
    public String toString() {
        if (printrSymbol == null) return super.toString();
        if (printrSymbol.isNonTerminal()) return printrSymbol.getNonTerminal().toString();

        String s = Terminal.terminalNames[printrSymbol.getTerminal().sym];
        // Devuelve valor contenido en el simbolo
        if (printrSymbol.getTerminal().value != null) s += ": " + printrSymbol.getTerminal().value;
        return s;
    }
}
