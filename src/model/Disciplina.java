package com.universidade.matricula;

import java.util.List;
import java.util.ArrayList;

public class Disciplina {
    private String nome;
    private int creditos;
    private TipoDisciplina tipo;
    private List<Aluno> alunosMatriculados;
    private List<Professor> professores;
    private boolean ativa;
    private int numAlunosInscritos;

    public Disciplina(String nome, int creditos, TipoDisciplina tipo) {
        this.nome = nome;
        this.creditos = creditos;
        this.tipo = tipo;
        this.alunosMatriculados = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.ativa = false;
        this.numAlunosInscritos = 0;
    }

    public void verificarAtivacao() {
        // Stub do método
        this.ativa = this.numAlunosInscritos >= 3;
        if (!this.ativa) {
            System.out.println("Disciplina " + this.nome + " cancelada por falta de alunos.");
        }
    }

    public boolean matricularAluno(Aluno aluno) {
        // Stub do método
        if (this.numAlunosInscritos < 60) {
            this.alunosMatriculados.add(aluno);
            this.numAlunosInscritos++;
            System.out.println("Aluno " + aluno.getNome() + " matriculado em " + this.nome);
            return true;
        } else {
            System.out.println("Não foi possível matricular o aluno " + aluno.getNome() + " em " + this.nome + ". Limite de alunos atingido.");
            return false;
        }
    }

    public void cancelarMatricula(Aluno aluno) {
        // Stub do método
        if (this.alunosMatriculados.remove(aluno)) {
            this.numAlunosInscritos--;
            System.out.println("Matrícula do aluno " + aluno.getNome() + " em " + this.nome + " cancelada.");
        }
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public TipoDisciplina getTipo() {
        return tipo;
    }

    public void setTipo(TipoDisciplina tipo) {
        this.tipo = tipo;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public int getNumAlunosInscritos() {
        return numAlunosInscritos;
    }

    public void setNumAlunosInscritos(int numAlunosInscritos) {
        this.numAlunosInscritos = numAlunosInscritos;
    }
}


