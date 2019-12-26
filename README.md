# NFeLab

Leitura de arquivos XML referentes a NFe.

### Comando para gerar as classes com base no XSD utilizando o JAXB
Os arquivos do tipo XSD, estão disponíveis no [Portal da Nota Fiscal Eletrônica](http://www.nfe.fazenda.gov.br/portal/listaConteudo.aspx?tipoConteudo=/fwLvLUSmU8=).

> Após baixar os arquivos, entrar na pasta e executar o comando abaixo:

```sh
$ xjc leiauteNFe_v4.00.xsd -d src -p br.com.valdineireis.nfelab.dto -encoding "UTF-8"
```

O **xjc** é um comando que já vem no JDK.