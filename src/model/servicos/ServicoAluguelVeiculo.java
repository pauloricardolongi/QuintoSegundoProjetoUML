package model.servicos;

import model.entidades.AluguelVeiculo;
import model.entidades.Fatura;

public class ServicoAluguelVeiculo {
	
	private Double precoPerDia;
	private Double precoPorHora;
	
	private TaxaServicoBrasil taxaServico;

	public ServicoAluguelVeiculo(Double precoPerDia, Double precoPorHora, TaxaServicoBrasil taxaServico) {
		
		this.precoPerDia = precoPerDia;
		this.precoPorHora = precoPorHora;
		this.taxaServico = taxaServico;
	}
	
	public void processoFatura(AluguelVeiculo aluguelVeiculo) {
		long t1 = aluguelVeiculo.getInicio().getTime();
		long t2 = aluguelVeiculo.getFim().getTime();
		double horas = (double) (t2 - t1)/1000/60/60; //horas
		
		double pagamentoBasico;
		if (horas <= 12.0) {
		    pagamentoBasico = Math.ceil(horas) * precoPorHora;
		}
		else {
			   pagamentoBasico = Math.ceil(horas/24) * precoPerDia;
		}
		//calcular imposto
		double imposto = taxaServico.taxa(pagamentoBasico);
		
		aluguelVeiculo.setFatura(new Fatura(pagamentoBasico, imposto));
		
	}

}
