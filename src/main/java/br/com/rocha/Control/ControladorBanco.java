package br.com.rocha.Control;

import java.util.ArrayList;
import java.util.List;

import br.com.rocha.BancoBean;
import br.com.rocha.Model.HibernateFactory;
import br.com.rocha.Model.entidades.BancoVO;

/**
 * Classe respons�vel em controlar as a��es do objeto Banco.
 * 
 * @author Lucas
 * 
 */
public class ControladorBanco {

	/**
	 * M�todo respons�vel em carregar uma lista de BancoBen.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BancoBean> pesquisarBancos() throws Exception {
		HibernateFactory hibernateFactory = new HibernateFactory();
		
		List<BancoVO> listBancoVO = hibernateFactory.getIBanco().pesquisarBancos();

		List<BancoBean> listBancoView = null;

		if (listBancoVO != null) {
			listBancoView = new ArrayList<BancoBean>();
			BancoBean bean = null;
			for (BancoVO bancoVO : listBancoVO) {
				bean = new BancoBean();
				bean.setCodigoBanco(bancoVO.getIdBanco());
				bean.setNomeBanco(bancoVO.getNomeBanco());
				listBancoView.add(bean);
			}
		}
		return listBancoView;
	}
}
