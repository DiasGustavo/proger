package br.com.proger.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.proger.dao.AcaoDAO;
import br.com.proger.domain.Acao;

@FacesConverter("acaoConverter")
public class AcaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			AcaoDAO acdao = new AcaoDAO();
			Acao acao = acdao.buscarPorCodigo(codigo);
			
			return acao;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Acao acao = (Acao) objeto;
			Long codigo = acao.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
