package br.com.proger.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.proger.dao.AtividadeDAO;
import br.com.proger.domain.Atividade;

@FacesConverter("atividadeConverter")
public class AtividadeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			AtividadeDAO atdao = new AtividadeDAO();
			Atividade atividade = atdao.buscarPorCodigo(codigo);
			
			return atividade;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Atividade atividade = (Atividade) objeto;
			Long codigo = atividade.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
