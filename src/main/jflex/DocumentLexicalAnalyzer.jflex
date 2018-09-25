// inicio user code

package cr.ac.ucr.ci1322;

%% // fin de user code, inicio de options and declarations

%public
%class DocumentLexicalAnalyzer
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
andOperator = AND|Y
orOperator = OR|O
notOperator = NOT|NO
preposicion = al?|ante|bajo|con|de|desde|durante|en|entre|excepto|hacia|hasta|mediante|para|por|salvo|según|sin|sobre|tras
articulo = el|las?|los?|uno?|unos|una?|unas
termino  = -?[a-zA-Z_]\w*

%% // fin de options and declarations, inicio de lexical rules
<YYINITIAL> {
    {space}                         {;}
    {preposicion}                   {System.out.println(yytext()+" es una preposición.");}
    {articulo}                      {System.out.println(yytext()+" es un artículo.");}
    {andOperator}                   {System.out.println(yytext()+" es un término.");}
    {orOperator}                    {System.out.println(yytext()+" es un término.");}
    {notOperator}                   {System.out.println(yytext()+" es un término.");}
    {termino}                       {System.out.println(yytext()+" es un término.");}
}

[^]                                 { System.out.println(yytext()+ " no es útil.");}
