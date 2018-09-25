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
    private boolean isDocument = false;

    public void setIsDocument(boolean isDocument) {
        this.isDocument = isDocument;
    }
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
    {andOperator}                   {if(this.isDocument){
                                        System.out.println(yytext()+" es un término.");
                                    } else {
                                        System.out.println(yytext()+" es un operador de conjunción.");
                                    }}
    {orOperator}                   {if(this.isDocument){
                                        System.out.println(yytext()+" es un término.");
                                    } else {
                                        System.out.println(yytext()+" es un operador de disyunción.");
                                    }}
    {notOperator}                   {if(this.isDocument){
                                        System.out.println(yytext()+" es un término.");
                                    } else {
                                        System.out.println(yytext()+" es un operador de negación.");
                                    }}
    {termino}                       {System.out.println(yytext()+" es un término.");}
}

[^]                                 { System.out.println(yytext()+ " no es útil.");}
