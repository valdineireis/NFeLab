package br.com.valdineireis.nfelab;

import br.com.valdineireis.nfelab.fabrica.LeitorXmlNFe;
import br.com.valdineireis.nfelab.dto.TNfeProc;
import java.io.IOException;

/**
 *
 * @author Valdinei
 */
public class Leitor {
    
    public static void main(String[] args) {
        String diretorio = "C:\\dev\\nfe";
        
        try {
            LeitorXmlNFe nfe = new LeitorXmlNFe(diretorio);
            
            System.out.println("Total de arquivos xml encontrados: " + nfe.getTotalArquivos());
            System.out.println("------------------------------------------------------------------------");
            
            for (TNfeProc wNfe : nfe.getTNfeProcList()) {
                if (wNfe != null) {
                    info("| Destinatario[CNPJ]........: " + wNfe.getNFe().getInfNFe().getDest().getCNPJ());
                    info("| Destinatario[Nome]........: " + wNfe.getNFe().getInfNFe().getDest().getXNome());
                    info("| Emitente[CNPJ]............: " + wNfe.getNFe().getInfNFe().getEmit().getCNPJ());
                    info("| Emitente[Nome]............: " + wNfe.getNFe().getInfNFe().getEmit().getXNome());

                    System.out.println(wNfe.getNFe().getInfNFe().getDet().size());
                    for (int i = 0; i < wNfe.getNFe().getInfNFe().getDet().size(); i++) {
                        info("| Item[Nome]................: " + wNfe.getNFe().getInfNFe().getDet().get(i).getProd().getXProd());
                    }
                }

                System.out.println("------------------------------------------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            error(e.toString());
        }
    }

    private static void info(String log) {
        System.out.println("INFO: " + log);
    }

    private static void error(String log) {
        System.out.println("ERROR: " + log);
    }
}
