package br.com.proger.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.proger.dao.OrgaoDAO;
import br.com.proger.domain.Orgao;



@FacesConverter("orgaoConverter")
public class OrgaoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			OrgaoDAO odao = new OrgaoDAO();
			Orgao orgao = odao.buscarPorCodigo(codigo);
			
			return orgao;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Orgao orgao = (Orgao) objeto;
			Long codigo = orgao.getId();
			
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
