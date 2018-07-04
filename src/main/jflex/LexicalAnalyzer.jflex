// inicio user code

package cr.ac.ucr.ci1322;

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
    private int errors;

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

    public int getErrors() {
        return errors;
    }
%}

newlines     = [\r\n]+
identifier  = -?[a-zA-Z_]\w*
integer     = \d+
read        = read
print       = print

%% // fin de options and declarations, inicio de lexical rules
<YYINITIAL> {
    {read}                          {return symbol(Terminal.T_READ);}
    {print}                         {return symbol(Terminal.T_PRINT);}
    {identifier}                    {return symbol(Terminal.T_IDENTIFIER,yytext());}
    {integer}                       {return symbol(Terminal.T_INTEGER,new Integer(yytext()));}
    "="                             {return symbol(Terminal.T_EQUALS);}
    "("                             {return symbol(Terminal.T_LEFT_PARENTHESIS);}
    ")"                             {return symbol(Terminal.T_RIGHT_PARENTHESIS);}
    " "                             {;}
    {newlines}                      {return symbol(Terminal.T_NEWLINES);}
    [^]                 { ++errors; ErrorReporter.lexicalError(yytext(), yyline + 1, yycolumn); }
}

[^]                 { ++errors; ErrorReporter.lexicalError(yytext(), yyline + 1, yycolumn); }
