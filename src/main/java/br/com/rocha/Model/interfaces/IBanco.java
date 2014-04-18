package br.com.rocha.Model.interfaces;

import java.util.List;

import br.com.rocha.Model.entidades.BancoVO;

/**
 * Interface que representa o DAO de Banco.
 * 
 * @author Lucas
 * 
 */
public interface IBanco {

	public List<BancoVO> pesquisarBancos() throws Exception;

}
