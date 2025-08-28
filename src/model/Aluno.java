package com.universidade.matricula;

import java.util.List;
import java.util.ArrayList;

public class Aluno extends Usuario {
    private String matricula;
    private String nome;
    private List<Disciplina> obrigatoriasMatriculadas;
    private List<Disciplina> optativasMatriculadas;

    public Aluno(String login, String senha, String matricula, String nome) {
        super(login, senha);
        this.matricula = matricula;
        this.nome = nome;
        this.obrigatoriasMatriculadas = new ArrayList<>();
        this.optativasMatriculadas = new ArrayList<>();
    }

    public boolean matricularObrigatoria(Disciplina disciplina) {
        // Stub do método
        if (this.obrigatoriasMatriculadas.size() < 4) {
            this.obrigatoriasMatriculadas.add(disciplina);
            System.out.println("Aluno " + this.nome + " matriculado na disciplina obrigatória " + disciplina.getNome());
            return true;
        } else {
            System.out.println("Limite de disciplinas obrigatórias atingido.");
            return false;
        }
    }

    public boolean matricularOptativa(Disciplina disciplina) {
        // Stub do método
        if (this.optativasMatriculadas.size() < 2) {
            this.optativasMatriculadas.add(disciplina);
            System.out.println("Aluno " + this.nome + " matriculado na disciplina optativa " + disciplina.getNome());
            return true;
        } else {
            System.out.println("Limite de disciplinas optativas atingido.");
            return false;
        }
    }

    public boolean cancelarMatriculaDisciplina(Disciplina disciplina) {
        // Stub do método
        if (this.obrigatoriasMatriculadas.remove(disciplina) || this.optativasMatriculadas.remove(disciplina)) {
            System.out.println("Matrícula na disciplina " + disciplina.getNome() + " cancelada.");
            return true;
        } else {
            System.out.println("Aluno não matriculado na disciplina " + disciplina.getNome());
            return false;
        }
    }

    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Disciplina> getObrigatoriasMatriculadas() {
        return obrigatoriasMatriculadas;
    }

    public void setObrigatoriasMatriculadas(List<Disciplina> obrigatoriasMatriculadas) {
        this.obrigatoriasMatriculadas = obrigatoriasMatriculadas;
    }

    public List<Disciplina> getOptativasMatriculadas() {
        return optativasMatriculadas;
    }

    public void setOptativasMatriculadas(List<Disciplina> optativasMatriculadas) {
        this.optativasMatriculadas = optativasMatriculadas;
    }
}


