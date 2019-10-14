package br.com.proger.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.proger.dao.PessoaFisicaDAO;
import br.com.proger.domain.PessoaFisica;



@FacesConverter("pessoaFisicaConverter")
public class PessoaFisicaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			PessoaFisica pfisica = pfdao.buscarPorCodigo(codigo);
			
			return pfisica;
			
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			PessoaFisica pfisica = (PessoaFisica) objeto;
			Long codigo = pfisica.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
		
			
	}

}
