package iniflex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantidade = 0;
        int opcoes = 0;

        List<Funcionario> listaFuncionario = new ArrayList<>();

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar funcionários");
            System.out.println("2. Listar funcionários");
            System.out.println("13. Sair");

            opcoes = scanner.nextInt();

            switch (opcoes) {
                case 1:
                    System.out.println("Informe a quantidade de funcionários a serem cadastrados:");
                    quantidade = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < quantidade; i++) {
                        Funcionario funcionario = new Funcionario();
                        System.out.println("Informe o nome do funcionário:");
                        funcionario.setNome(scanner.nextLine());

                        System.out.println("Informe a data de nascimento (dd/MM/yyyy):");
                        String dataNascimentoStr = scanner.nextLine();
                        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, dataFormatada);
                        funcionario.setDataNascimento(dataNascimento);

                        System.out.println("Informe o salário:");
                        funcionario.setSalario(scanner.nextBigDecimal());
                        scanner.nextLine(); // Capturar o caractere de nova linha restante

                        System.out.println("Informe a função:");
                        funcionario.setFuncao(scanner.nextLine());

                        listaFuncionario.add(funcionario);
                    }
                    break;

                case 2:
                    System.out.println("Lista de funcionários:");
                    for (Funcionario funcionario : listaFuncionario) {
                        System.out.println(funcionario);
                    }
                    break;

                case 3:
                    System.out.println("Digite o nome do funcionário que deseja remover:");
                    String nome = scanner.nextLine();
                    for (Funcionario funcionario : listaFuncionario) {
                        if(nome.equalsIgnoreCase(funcionario.getNome())) {
                            listaFuncionario.remove(funcionario);
                            System.out.println("model.Funcionario: " + funcionario.getNome() + ", foi removido com sucesso!");
                        }
                        else{
                            System.out.println("Funcionário não encontrado");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcoes != 4);

        scanner.close();
    }
}
