package br.com.proger.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.proger.dao.PessoaJuridicaDAO;
import br.com.proger.domain.PessoaJuridica;



@FacesConverter("pessoaJuridicaConverter")
public class PessoaJuridicaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try{
			Long codigo = Long.parseLong(valor);
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			PessoaJuridica pj = pjdao.buscarPorCodigo(codigo);
			
			return pj;
		}catch(RuntimeException ex){
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try{
			PessoaJuridica pj = (PessoaJuridica) objeto;
			Long codigo = pj.getId();
			
			return codigo.toString();
			
		}catch(RuntimeException ex){
			return null;
		}
	}

}
