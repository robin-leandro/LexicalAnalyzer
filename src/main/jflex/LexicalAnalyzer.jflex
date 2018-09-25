// inicio user code

package cr.ac.ucr.ci1322;

%% // fin de user code, inicio de options and declarations

%public
%class LexicalAnalyzer
%standalone

%ignorecase
%caseless
%unicode
%line
%column
%eofval{
%eofval}

%{

%}

space = \s+
andOperator = AND
orOperator = OR
notOperator = NOT
preposicion = al?|ante|bajo|con|de|desde|durante|en|entre|excepto|hacia|hasta|mediante|para|por|salvo|según|sin|sobre|tras
articulo = el|las?|los?|uno?|unos|una?|unas
termino  = -?[a-zA-Z_]\w*

%% // fin de options and declarations, inicio de lexical rules
<YYINITIAL> {
    {space}                         {;}
    {preposicion}                   {System.out.println(yytext()+" es una preposicion");}
    {articulo}                      {System.out.println(yytext()+" es un articulo");}
    {andOperator}                   {System.out.println(yytext()+" es un operador AND");}
    {orOperator}                    {System.out.println(yytext()+" es un operador OR");}
    {notOperator}                   {System.out.println(yytext()+" es un operador NOT");}
    {termino}                       {System.out.println(yytext()+" es un termino");}
}

[^]                                 { System.out.println(yytext()+ " no es útil.");}
