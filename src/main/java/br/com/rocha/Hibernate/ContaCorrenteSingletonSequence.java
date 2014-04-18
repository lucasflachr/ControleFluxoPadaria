package br.com.rocha.Hibernate;

import br.com.rocha.Control.ControladorContaCorrente;

public class ContaCorrenteSingletonSequence {
	
	private Integer sequenceConta = new Integer(0);
	
	private static ContaCorrenteSingletonSequence instance;
			
	private ContaCorrenteSingletonSequence() {		
		try {
			sequenceConta = new ControladorContaCorrente().carregarMaiorCodigoConta();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ContaCorrenteSingletonSequence getInstance() {
		if (instance == null)
			instance = new ContaCorrenteSingletonSequence();
		
		return instance;
	}
	
	public Integer getSequenceConta() {
		if (sequenceConta == null)
			sequenceConta = new Integer(0);
		
		return ++sequenceConta;
	}
}
