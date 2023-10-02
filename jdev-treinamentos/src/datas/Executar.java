package datas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;

public class Executar {
	public static void main(String[] args) throws ParseException, InterruptedException {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		
		/*System.out.println(sd.parse("23-05-23"));
		
		Calendar calendar = Calendar.getInstance();
		
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(sd.format(calendar.getTime()));
		
		Date data1 = sd.parse("19-10-2023");
		Date data2 = sd.parse("10-09-2023");
		
		System.out.println(data1.after(data2) ? "data 1 é maior" : "data 2 é maior");
		
		calendar.add(Calendar.DAY_OF_MONTH, -20);
		System.out.println(sd.format(calendar.getTime()));
		*/
		/*Faixa de tempo*/
		long dias = ChronoUnit.DAYS.between(LocalDate.parse("2022-08-16"), LocalDate.now());
		System.out.println(dias);
		
		LocalDate dataAtual = LocalDate.now();
		System.out.println(dataAtual.format(DateTimeFormatter.ISO_DATE));
		System.out.println(dataAtual.format(DateTimeFormatter.ofPattern("dd 'de' MM 'de' yyyy")));

		System.out.println(dataAtual.getMonth().name());
		
		Instant instant = Instant.now();
		
		//Thread.sleep(2000);
		
		Instant finaInstant = Instant.now();
		
		Duration duration = Duration.between(instant, finaInstant);

		System.out.println(duration.getSeconds());
		
		LocalDate vencimento = LocalDate.parse("2023-05-28");
		
		LocalDate dataAtual1 = LocalDate.now();
		
		System.out.println("Vencimento é maior: (não vencido)" + vencimento.isAfter(dataAtual1) );
		
		System.out.println("Vencimento é menor (vencido): " + vencimento.isBefore(dataAtual1));
		
		Period period = Period.between(vencimento, dataAtual1);
		
		System.out.println(period.getMonths() + " " + period.getDays());
		
		
	}

}
