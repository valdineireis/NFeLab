package br.com.valdineireis.nfelab.fabrica;

import br.com.valdineireis.nfelab.dto.TNfeProc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Valdinei
 */
public class LeitorXmlNFe {

    private final int totalArquivos;
    private final List<TNfeProc> tNfeProcList;
    
    public LeitorXmlNFe(String diretorio) throws IOException, Exception {
        tNfeProcList = new ArrayList<>();
        
        File[] files = getArquivosXml(diretorio);
        totalArquivos = files.length;
        
        for (File f : files) {
            String xml = lerXML(f.getAbsoluteFile().toString());
            TNfeProc wNfe = getNFe(xml);
            tNfeProcList.add(wNfe);
        }
    }

    public int getTotalArquivos() {
        return totalArquivos;
    }

    public List<TNfeProc> getTNfeProcList() {
        return tNfeProcList;
    }
    
    private FileFilter getFiltroXml() {
        FileFilter xmlFilefilter = (File file) -> {
            return file.getName().endsWith(".xml");
        };
        return xmlFilefilter;
    }

    private File[] getArquivosXml(String diretorio) throws IOException {
        File directory = new File(diretorio);
        // Verifica se é um nome de arquivo válido
        if (!directory.exists()) {
            throw new IOException(String.format("O diretório %s não existe", diretorio));
        }
        // Verifiqua se é um diretório e não um caminho de arquivo
        if (!directory.isDirectory()) {
            throw new IOException(String.format("O valor informado %s não é um diretório", diretorio));
        }
        File[] files = directory.listFiles(getFiltroXml());
        // Lista de arquivos filtrados
        return files;
    }

    private String lerXML(String fileXML) throws IOException {
        String linha;
        StringBuilder xml = new StringBuilder();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileXML)))) {
            while ((linha = in.readLine()) != null) {
                xml.append(linha);
            }
        }

        return xml.toString();
    }
    
    private TNfeProc getNFe(String xml) throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(TNfeProc.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            TNfeProc nfe = unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), TNfeProc.class).getValue();
            return nfe;
        } catch (JAXBException ex) {
            throw new Exception(ex.toString());
        }
    }
}
