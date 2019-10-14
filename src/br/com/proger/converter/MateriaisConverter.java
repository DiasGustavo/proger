package br.com.proger.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.proger.dao.MateriaisDAO;
import br.com.proger.domain.Materiais;

@FacesConverter("materiaisConverter")
public class MateriaisConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			MateriaisDAO mdao = new MateriaisDAO();
			Materiais materiais = mdao.buscarPorCodigo(codigo);
			
			return materiais;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			Materiais materiais = (Materiais) objeto;
			Long codigo = materiais.getId();
			return codigo.toString();
		}catch(RuntimeException ex){
			return null;
		}
	}

}
