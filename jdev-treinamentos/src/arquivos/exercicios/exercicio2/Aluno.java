package arquivos.exercicios.exercicio2;

public class Aluno {

	private Long matricula;
	
	private String nome;
	
	private String turma;
	
	public Aluno() {
	}

	public Aluno(Long matricula, String nome, String turma) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.turma = turma;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", turma=" + turma + "]";
	}
	
	
	
}
