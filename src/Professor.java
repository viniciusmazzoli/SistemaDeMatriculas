
package com.universidade.matricula;

import java.util.List;
import java.util.ArrayList;

public class Professor extends Usuario {
    private String siape;
    private String nome;
    private List<Disciplina> disciplinasLecionadas;

    public Professor(String login, String senha, String siape, String nome) {
        super(login, senha);
        this.siape = siape;
        this.nome = nome;
        this.disciplinasLecionadas = new ArrayList<>();
    }

    public List<Aluno> verAlunosMatriculados(Disciplina disciplina) {
        // Stub do método
        System.out.println("Professor " + this.nome + " visualizando alunos matriculados em " + disciplina.getNome());
        return disciplina.getAlunosMatriculados();
    }

    // Getters e Setters
    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Disciplina> getDisciplinasLecionadas() {
        return disciplinasLecionadas;
    }

    public void setDisciplinasLecionadas(List<Disciplina> disciplinasLecionadas) {
        this.disciplinasLecionadas = disciplinasLecionadas;
    }
}


