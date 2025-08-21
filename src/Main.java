package com.universidade.matricula;

import java.util.Date;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static SistemaDeMatriculas sistema = new SistemaDeMatriculas();
    private static Universidade minhaUniversidade = new Universidade("Universidade Exemplo");

    public static void main(String[] args) {
        // Configuração inicial (dados de exemplo)
        setupInitialData();

        int opcao;
        do {
            System.out.println("\n--- Sistema de Matrículas ---");
            System.out.println("1. Login");
            System.out.println("2. Visualizar Cursos e Disciplinas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = getIntInput();

            switch (opcao) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    viewCoursesAndDisciplines();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void setupInitialData() {
        // Criando cursos
        Curso engenhariaSoftware = new Curso("Engenharia de Software", 200);
        minhaUniversidade.adicionarCurso(engenhariaSoftware);

        // Criando disciplinas
        Disciplina poo = new Disciplina("Programação Orientada a Objetos", 4, TipoDisciplina.OBRIGATORIA);
        Disciplina bancoDados = new Disciplina("Banco de Dados", 4, TipoDisciplina.OBRIGATORIA);
        Disciplina redes = new Disciplina("Redes de Computadores", 4, TipoDisciplina.OPTATIVA);
        Disciplina calculo = new Disciplina("Cálculo I", 6, TipoDisciplina.OBRIGATORIA);

        engenhariaSoftware.adicionarDisciplina(poo);
        engenhariaSoftware.adicionarDisciplina(bancoDados);
        engenhariaSoftware.adicionarDisciplina(redes);
        engenhariaSoftware.adicionarDisciplina(calculo);

        // Criando alunos
        Aluno aluno1 = new Aluno("aluno1", "senha123", "12345", "João Silva");
        Aluno aluno2 = new Aluno("aluno2", "senha456", "67890", "Maria Souza");
        Aluno aluno3 = new Aluno("aluno3", "senha789", "11223", "Pedro Costa");

        // Criando professores
        Professor prof1 = new Professor("prof1", "profsenha", "P001", "Ana Paula");

        // Adicionando usuários ao sistema
        sistema.adicionarUsuario(aluno1);
        sistema.adicionarUsuario(aluno2);
        sistema.adicionarUsuario(aluno3);
        sistema.adicionarUsuario(prof1);

        // Adicionando disciplinas para teste de ativação
        poo.matricularAluno(aluno1);
        poo.matricularAluno(aluno2);
        poo.matricularAluno(aluno3);
        poo.verificarAtivacao(); // Deve ativar POO

        // Período de matrícula (exemplo)
        Date hoje = new Date();
        Date amanha = new Date(hoje.getTime() + (1000 * 60 * 60 * 24));
        PeriodoMatricula periodoAtual = new PeriodoMatricula(new Date(hoje.getTime() - (1000 * 60 * 60 * 24 * 7)), amanha); // Uma semana atrás até amanhã
        sistema.adicionarPeriodoMatricula(periodoAtual);
    }

    private static void handleLogin() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioLogado = null;
        for (Usuario u : sistema.getUsuarios()) {
            if (u.validarLogin(login, senha)) {
                usuarioLogado = u;
                break;
            }
        }

        if (usuarioLogado != null) {
            System.out.println("Login bem-sucedido! Bem-vindo(a), " + (usuarioLogado instanceof Aluno ? ((Aluno)usuarioLogado).getNome() : ((Professor)usuarioLogado).getNome()) + ".");
            if (usuarioLogado instanceof Aluno) {
                showAlunoMenu((Aluno) usuarioLogado);
            } else if (usuarioLogado instanceof Professor) {
                showProfessorMenu((Professor) usuarioLogado);
            }
        } else {
            System.out.println("Login ou senha inválidos.");
        }
    }

    private static void showAlunoMenu(Aluno aluno) {
        int opcao;
        do {
            System.out.println("\n--- Menu do Aluno ---");
            System.out.println("1. Matricular em Disciplina");
            System.out.println("2. Cancelar Matrícula");
            System.out.println("3. Visualizar Minhas Disciplinas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = getIntInput();

            switch (opcao) {
                case 1:
                    enrollInDiscipline(aluno);
                    break;
                case 2:
                    cancelEnrollment(aluno);
                    break;
                case 3:
                    viewMyDisciplines(aluno);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void enrollInDiscipline(Aluno aluno) {
        if (!isEnrollmentPeriodActive()) {
            System.out.println("Não é possível matricular-se: o período de matrículas não está ativo.");
            return;
        }

        System.out.println("\nDisciplinas disponíveis:");
        List<Disciplina> disciplinasDisponiveis = new ArrayList<>();
        int i = 1;
        for (Curso curso : minhaUniversidade.getCursos()) {
            for (Disciplina d : curso.getDisciplinas()) {
                System.out.println(i + ". " + d.getNome() + " (" + d.getTipo() + ") - Alunos: " + d.getNumAlunosInscritos() + "/60");
                disciplinasDisponiveis.add(d);
                i++;
            }
        }

        if (disciplinasDisponiveis.isEmpty()) {
            System.out.println("Nenhuma disciplina disponível no momento.");
            return;
        }

        System.out.print("Escolha o número da disciplina para matricular: ");
        int escolha = getIntInput();

        if (escolha > 0 && escolha <= disciplinasDisponiveis.size()) {
            Disciplina disciplinaEscolhida = disciplinasDisponiveis.get(escolha - 1);
            if (disciplinaEscolhida.getTipo() == TipoDisciplina.OBRIGATORIA) {
                aluno.matricularObrigatoria(disciplinaEscolhida);
            } else {
                aluno.matricularOptativa(disciplinaEscolhida);
            }
            disciplinaEscolhida.matricularAluno(aluno);
            sistema.notificarSistemaDeCobrancas(aluno, aluno.getObrigatoriasMatriculadas()); // Notifica apenas obrigatórias para simplificar
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void cancelEnrollment(Aluno aluno) {
        if (!isEnrollmentPeriodActive()) {
            System.out.println("Não é possível cancelar matrícula: o período de matrículas não está ativo.");
            return;
        }

        System.out.println("\nSuas disciplinas matriculadas:");
        List<Disciplina> minhasDisciplinas = new ArrayList<>();
        minhasDisciplinas.addAll(aluno.getObrigatoriasMatriculadas());
        minhasDisciplinas.addAll(aluno.getOptativasMatriculadas());

        if (minhasDisciplinas.isEmpty()) {
            System.out.println("Você não está matriculado em nenhuma disciplina.");
            return;
        }

        int i = 1;
        for (Disciplina d : minhasDisciplinas) {
            System.out.println(i + ". " + d.getNome() + " (" + d.getTipo() + ")");
            i++;
        }

        System.out.print("Escolha o número da disciplina para cancelar a matrícula: ");
        int escolha = getIntInput();

        if (escolha > 0 && escolha <= minhasDisciplinas.size()) {
            Disciplina disciplinaParaCancelar = minhasDisciplinas.get(escolha - 1);
            aluno.cancelarMatriculaDisciplina(disciplinaParaCancelar);
            disciplinaParaCancelar.cancelarMatricula(aluno);
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void viewMyDisciplines(Aluno aluno) {
        System.out.println("\n--- Minhas Disciplinas ---");
        if (aluno.getObrigatoriasMatriculadas().isEmpty() && aluno.getOptativasMatriculadas().isEmpty()) {
            System.out.println("Você não está matriculado em nenhuma disciplina.");
            return;
        }
        System.out.println("Obrigatórias:");
        aluno.getObrigatoriasMatriculadas().forEach(d -> System.out.println("- " + d.getNome()));
        System.out.println("Optativas:");
        aluno.getOptativasMatriculadas().forEach(d -> System.out.println("- " + d.getNome()));
    }

    private static void showProfessorMenu(Professor professor) {
        int opcao;
        do {
            System.out.println("\n--- Menu do Professor ---");
            System.out.println("1. Visualizar Alunos por Disciplina");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = getIntInput();

            switch (opcao) {
                case 1:
                    viewStudentsByDiscipline(professor);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void viewStudentsByDiscipline(Professor professor) {
        System.out.println("\nDisciplinas disponíveis para visualização:");
        List<Disciplina> todasDisciplinas = new ArrayList<>();
        int i = 1;
        for (Curso curso : minhaUniversidade.getCursos()) {
            for (Disciplina d : curso.getDisciplinas()) {
                System.out.println(i + ". " + d.getNome());
                todasDisciplinas.add(d);
                i++;
            }
        }

        if (todasDisciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }

        System.out.print("Escolha o número da disciplina para ver os alunos: ");
        int escolha = getIntInput();

        if (escolha > 0 && escolha <= todasDisciplinas.size()) {
            Disciplina disciplinaEscolhida = todasDisciplinas.get(escolha - 1);
            List<Aluno> alunos = professor.verAlunosMatriculados(disciplinaEscolhida);
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno matriculado nesta disciplina.");
            } else {
                System.out.println("Alunos matriculados em " + disciplinaEscolhida.getNome() + ":");
                alunos.forEach(a -> System.out.println("- " + a.getNome() + " (Matrícula: " + a.getMatricula() + ")"));
            }
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void viewCoursesAndDisciplines() {
        System.out.println("\n--- Cursos e Disciplinas ---");
        if (minhaUniversidade.getCursos().isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }
        for (Curso curso : minhaUniversidade.getCursos()) {
            System.out.println("Curso: " + curso.getNome() + " (Créditos: " + curso.getCreditos() + ")");
            if (curso.getDisciplinas().isEmpty()) {
                System.out.println("  Nenhuma disciplina neste curso.");
            } else {
                for (Disciplina d : curso.getDisciplinas()) {
                    System.out.println("  - " + d.getNome() + " (Créditos: " + d.getCreditos() + ", Tipo: " + d.getTipo() + ", Ativa: " + d.isAtiva() + ", Alunos: " + d.getNumAlunosInscritos() + ")");
                }
            }
        }
    }

    private static boolean isEnrollmentPeriodActive() {
        for (PeriodoMatricula p : sistema.getPeriodosMatricula()) {
            if (p.estaAtivo()) {
                return true;
            }
        }
        return false;
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next(); // Consome a entrada inválida
            System.out.print("Escolha uma opção: ");
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha pendente
        return input;
    }
}


