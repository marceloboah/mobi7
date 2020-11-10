# mobi7

# Instructions to run this application

*** Make sure JDK 1.8 is installed and configured ***
Link for download --> https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html

Esta é uma aplicação que le dois arquivos csv, armazena em um banco de dados em memória, gera dados na tabela Metrics e expõe um endpoint para consultar e pesquisar esta informação.

Esta aplicação está disponível no git:

https://github.com/marceloboah/mobi7

1)Para executar a aplicação basta
abrir o prompt de comando e acessar dentro da pasta target do projeto a aplicação mobi-api em arquivo  jar.

\crud-spring-boot-modified\target\mobi-api-1.0.0-SNAPSHOT.jar

Atenção para mudanças no nome do arquivo referente a alterções na versão.

Para encerrar a aplicação efetue ctrl+c no prompt em execução.


2)Antes de iniciar a execução do arquivo crie uma pasta data na raiz:

C:\data

Em seguida coloque os arquivos de leitura csv dentro da pasta.

3)Esta aplicação funciona com um banco de dados em memória do tipo H2.

Foi escolhida a opção com escrita em arquivo.

Crie também uma pasta h2 dentro da pasta data para o bom funcionamento do BD em memória.

O banco de dados pode ser acessado pelo link:

http://localhost:9099/h2

Para acessar utilize

JDBC URL:   jdbc:h2:file:C:/data/h2/mh2

User Name:  sa

Password:



4) Para executar a carga do arquivo POIS acesse:

http://localhost:9099/api/import/pois

5) Para executar a carga do arquivo de POSIÇÕES acesse:

http://localhost:9099/api/import/positions

6) Para gerar as metricas acesse:

http://localhost:9099/api/metrics/generate

7) O menu da aplicação pode ser acessível por:

http://localhost:9099

@autor Marcelo Boá
@since 07/11/2020



