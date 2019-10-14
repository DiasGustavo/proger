package br.com.proger.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.proger.dao.ProfissaoDAO;
import br.com.proger.domain.Profissao;

@FacesConverter("profissaoConverter")
public class ProfissaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			ProfissaoDAO pdao = new ProfissaoDAO();
			Profissao profissao = pdao.buscarPorCodigo(codigo);
			
			return profissao;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Profissao profissao = (Profissao) objeto;
			Long codigo = profissao.getId();
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
