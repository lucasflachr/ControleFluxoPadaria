package br.com.rocha.Model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.rocha.Model.entidades.BancoVO;
import br.com.rocha.Model.interfaces.IBanco;
import br.com.rocha.Hibernate.HibernateUtil;

/**
 * Classe respons�vel em manipular os dados da entidade de Banco.
 * 
 * @author Lucas
 * 
 */
public class BancoDAO implements IBanco {

	/**
	 * M�todo respons�vel em pesquisar todos os bancos.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BancoVO> pesquisarBancos() throws Exception {
		List<BancoVO> list = null;
		try {

			Session session = HibernateUtil.currentSession();

			Query query = session.createQuery("select b from BancoVO b ");

			list = (List<BancoVO>) query.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
}
