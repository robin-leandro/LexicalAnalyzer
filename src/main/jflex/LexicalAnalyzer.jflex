// inicio user code

package cr.ac.ucr.ci1322;

import cr.ac.ucr.ci1322.exceptions.LexicalErrorException;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;

%% // fin de user code, inicio de options and declarations

%public
%class LexicalAnalyzer
%standalone

%unicode
%line
%column
%cupsym Terminal
%cup
%eofval{
    Location left  = new Location(yyline + 1, yycolumn);
    Location right = new Location(yyline + 1, yycolumn + yylength());
    return symbolFact.newSymbol(Terminal.terminalNames[Terminal.EOF], Terminal.EOF, left, right);
%eofval}

%{
    // string literal
    private StringBuilder string = new StringBuilder();
    private ComplexSymbolFactory symbolFact;
    private boolean ok = true;

    public LexicalAnalyzer(java.io.Reader in, ComplexSymbolFactory sf) {
        this(in);
        symbolFact = sf;
    }

    // retorna el Symbol para el tipo de token encontrado con sus coordenadas.
    private Symbol symbol(int type){
        Location left  = new Location(yyline + 1, yycolumn);
        Location right = new Location(yyline + 1, yycolumn + yylength());
        return symbolFact.newSymbol(Terminal.terminalNames[type], type, left, right);
    }

    // retorna el Symbol para el tipo de token encontrado, junto a su valor y sus coordenadas
    private Symbol symbol(int type, Object value){
        Location left  = new Location(yyline + 1, yycolumn);
        Location right = new Location(yyline + 1, yycolumn + yylength());
        return symbolFact.newSymbol(Terminal.terminalNames[type], type, left, right, value);
    }

    // retorna el Symbol para el tipo de token encontrado, junto a su valor y sus coordenadas
    private Symbol symbol(int type, Object value, int column, int length){
        Location left  = new Location(yyline + 1, column);
        Location right = new Location(yyline + 1, column + length);
        return symbolFact.newSymbol(Terminal.terminalNames[type], type, left, right, value);
    }

    // Retorna Symbol error y el respectivo mensaje para darle control al analizador sintáctico
    private Symbol error(String value, String errtype){
        return symbol(Terminal.error,"LEXICAL ERROR: "+ value + " isn't a valid " + errtype + ", error on line("+ yyline + "), column(" + yycolumn+")");
    }

    public boolean isOk() {
        return ok;
    }
%}

andOperator = AND
orOperator = OR
notOperator = NOT
preposicion = a|ante|bajo|con|de|desde|durante|en|entre|excepto|hacia|hasta|mediante|para|por|salvo|según|sin|sobre|tras
articulo = el|las?|los?|uno?|unos|una?|unas
termino  = \D\w{3,30}\b

%% // fin de options and declarations, inicio de lexical rules
<YYINITIAL> {
    {preposicion}                   {System.out.print(yytext()+" es una preposicion");}
    {articulo}                      {System.out.print(yytext()+" es un articulo");}
    {andOperator}                   {System.out.print(yytext()+" es un operador AND");}
    {orOperator}                    {System.out.print(yytext()+" es un operador OR");}
    {notOperator}                   {System.out.print(yytext()+" es un operador NOT");}
    {termino}                       {System.out.print(yytext()+" es un termino");}
}

[^]                                 { System.out.println("Lexical error: unknown token "+yytext());ok=false;}
