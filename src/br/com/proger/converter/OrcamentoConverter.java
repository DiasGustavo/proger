package br.com.proger.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.proger.dao.OrcamentoDAO;
import br.com.proger.domain.Orcamento;

@FacesConverter("orcamentoConverter")
public class OrcamentoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			OrcamentoDAO odao = new OrcamentoDAO();
			Orcamento orcamento = odao.buscarPorCodigo(codigo);
			
			return orcamento;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Orcamento orcamento = (Orcamento) objeto;
			Long codigo = orcamento.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
