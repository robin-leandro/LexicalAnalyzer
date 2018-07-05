package cr.ac.ucr.ci1322;

import java.util.Arrays;

public class ParseTree {

    private Node root;

    public ParseTree(Node root) {
        this.root = root;
    }

    public ParseTree(){
        root = new Node();
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        toStringAux(root, stringBuilder, 0);
        return stringBuilder.toString();
    }

    private void toStringAux(Node node, StringBuilder str, int indent) {
        if(node == null) return;
        for (int i = 0; i < indent; ++i) {
            str.append("║\t");
        }
            str.append("╠═");
            str.append(node.toString());
            str.append("\n");


        // children puede estar vacio, pero no _deberia_ ser nulo
        for (Node n : node.getChildren()) {
            toStringAux(n, str, indent+1);
        }
    }
}
