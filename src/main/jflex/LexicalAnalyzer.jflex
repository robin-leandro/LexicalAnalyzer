// inicio user code

package cr.ac.ucr.ci1322;

%% // fin de user code, inicio de options and declarations

%public
%class LexicalAnalyzer
%standalone

%unicode
%line
%column
%eofval{
%eofval}

%{

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

[^]                                 { System.out.println("Lexical error: unknown token "+yytext());}
