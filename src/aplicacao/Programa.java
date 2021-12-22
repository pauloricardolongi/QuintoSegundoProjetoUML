package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entidades.AluguelVeiculo;
import model.entidades.Veiculo;
import model.servicos.ServicoAluguelVeiculo;
import model.servicos.TaxaServicoBrasil;

public class Programa {

	public static void main(String[] args)throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Entre com dados do aluguel");
		System.out.print(" Modelo Veículo");
		String modeloVeiculo = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy HH:ss)");
		Date inicio = sdf.parse(sc.nextLine());
		System.out.print("Retorno (dd/MM/yyyy HH:ss)");
		Date fim= sdf.parse(sc.nextLine());
		
		
		AluguelVeiculo cr = new AluguelVeiculo(inicio, fim, new Veiculo(modeloVeiculo));
		
		System.out.print("Entre preço por hora");
		double precoPorHora = sc.nextDouble();
		System.out.print("Entre preço por dia");
		double precoPerDia = sc.nextDouble();
		
		ServicoAluguelVeiculo sav = new ServicoAluguelVeiculo(precoPerDia, precoPorHora,new TaxaServicoBrasil());
		
		sav.processoFatura(cr);
		
		System.out.println("FATURA");
		System.out.println("pagamento Basico " + String.format("%.2f", cr.getFatura().getPagamentoBasico()));
		System.out.println("Imposto " + String.format("%.2f", cr.getFatura().getImposto()));
		System.out.println("Total pagamento " + String.format("%.2f", cr.getFatura().getTotalPagamento()));
		
		
		sc.close();

	}

}
