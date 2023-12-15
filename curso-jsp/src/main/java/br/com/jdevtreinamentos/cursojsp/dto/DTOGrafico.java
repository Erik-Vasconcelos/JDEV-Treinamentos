package br.com.jdevtreinamentos.cursojsp.dto;

import java.util.ArrayList;
import java.util.List;

public class DTOGrafico {
	List<String> perfis = new ArrayList<>();
	List<Double> salarios = new ArrayList<>();

	public List<String> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<String> perfis) {
		this.perfis = perfis;
	}

	public List<Double> getSalarios() {
		return salarios;
	}

	public void setSalarios(List<Double> salarios) {
		this.salarios = salarios;
	}

}
