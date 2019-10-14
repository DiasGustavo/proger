package br.com.proger.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.proger.dao.FinanceiroDAO;
import br.com.proger.domain.Financeiro;

@FacesConverter("financeiroConverter")
public class FinanceiroConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			FinanceiroDAO fdao = new FinanceiroDAO();
			Financeiro financeiro = fdao.buscarPorCodigo(codigo);
			
			return financeiro;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Financeiro financeiro = (Financeiro) objeto;
			Long codigo = financeiro.getId();
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
