package datas;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;

public class Exercicios {
	public static void main(String[] args) throws ParseException {
		
		//Calendar
		/*Crie duas datas como o calendar onde uma é a data do seu aniversário e a outra é a data atual.
		 * Para elas faça o seguinte: 
		 * 1 - Imprima quantos meses faltam
		 * 2 - imprima quantos dias faltam
		 * 3 - compare qual data é maior
		 * 4 - adicione um mes e vinte dias a data atual*/
		
		
		/*Calendar dataAniversario = Calendar.getInstance();
		dataAniversario.setTime(new SimpleDateFormat("dd/MM/yyyy").parse("24/01/2024"));
		
		Calendar dataAtual = Calendar.getInstance();
		
		System.out.println("Ainda vai ocorrer: " + dataAniversario.after(dataAtual));
		System.out.println("Já Ocorreu: " + dataAniversario.before(dataAtual));
		
		dataAtual.add(Calendar.DAY_OF_MONTH, 20);
		
		System.out.println(new SimpleDateFormat().format(dataAtual.getTime()));*/
		
		Consumer<Object> print = System.out::println;
		
		LocalDate dataAniversario = LocalDate.parse("2023-12-18");
		LocalDate dataAtual = LocalDate.now();
		
		long dias = ChronoUnit.DAYS.between(dataAtual, dataAniversario);
		long semanas = ChronoUnit.WEEKS.between(dataAtual, dataAniversario);
		long meses = ChronoUnit.MONTHS.between(dataAtual, dataAniversario);
		
		System.out.println("Dias: " + dias);
		System.out.println("Semanas: " + semanas);
		System.out.println("Meses: " + meses);
		
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		
		print.accept(dataHoraAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
		
		print.accept("Dia do mês: " + dataHoraAtual.getDayOfMonth());
		
		print.accept("--------------Instant--------------");
		
		Instant inicial = Instant.now();
		
		for (int i = 0; i < 300000; i++) {
			System.out.println("I: " + i);
		}
		
		Instant iFinal = Instant.now();
		
		Duration tempoDeProcessamento = Duration.between(inicial, iFinal);
		
		print.accept("Segundos" + tempoDeProcessamento.toSeconds());
		print.accept("Minutos" + tempoDeProcessamento.toMinutes());
		
		
		print.accept("Ainda vai ocorrer o niver: " + dataAtual.isBefore(dataAniversario));
		
		print.accept("O niver já ocorreu: " + dataAtual.isAfter(dataAniversario));
				
		Period tempoParaNiver = Period.between(dataAtual, dataAniversario);
		
		print.accept("Anos: " + tempoParaNiver.getYears() + " Meses: " + tempoParaNiver.getMonths() + " Dias: " + tempoParaNiver.getDays());
		
		dataAtual = dataAtual.plusDays(4);
		dataAtual = dataAtual.plusMonths(1);
		
		dataAtual = dataAtual.minusYears(1);
		
		print.accept("Adicionando mais tempo: " + dataAtual.format(DateTimeFormatter.ISO_DATE));
;	}

}
