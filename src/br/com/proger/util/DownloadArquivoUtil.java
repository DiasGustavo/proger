package br.com.proger.util;

import java.io.*;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


public class DownloadArquivoUtil {
	private StreamedContent arquivoArmazenado;
	
	
	public StreamedContent getArquivoArmazenado() {
		return arquivoArmazenado;
	}

	public StreamedContent visualizarArquivo(String localArquivo, String tipo){
		
		try {
			FileInputStream stream = new FileInputStream(localArquivo);
			this.arquivoArmazenado = new DefaultStreamedContent(stream, tipo, "arquivo.pdf");
		} catch (FileNotFoundException e) {
			FacesUtil.addMsgErro("Erro ao carregar o arquivo " + e.getMessage());
			e.printStackTrace();
		}
		
		return this.arquivoArmazenado;
	}

}
