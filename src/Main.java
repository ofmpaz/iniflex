import model.Funcionario;
import service.FuncionarioService;
import service.MontarMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcoes = 0;

        List<Funcionario> listaFuncionario = new ArrayList<>();
        FuncionarioService funcionarioService = new FuncionarioService();
        MontarMenu montarMenu = new MontarMenu();

        do {
            montarMenu.exibirMenu();

            opcoes = scanner.nextInt();

            switch (opcoes) {
                case 1:
                    funcionarioService.cadastroFuncionario();
                    break;

                case 2:
                    funcionarioService.listarFuncionarios();
                    break;

                case 3:
                    System.out.println("Digite o nome do funcionário que deseja remover:");
                    funcionarioService.removerFuncionario();
                    break;

                case 4:
                    funcionarioService.aumentoSalario();
                    break;

                case 5:
                    Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarioService.agruparPorFuncao();
                    funcionarioService.imprimirFuncionariosPorFuncao(funcionariosPorFuncao);
                    break;

                case 6:
                    funcionarioService.imprimirAniversários();
                    break;

                case 7:
                    funcionarioService.imprimirFuncionarioMaisVelho();
                    break;

                case 8:
                    funcionarioService.imprimirFuncionariosOrdemAlfabetica();
                    break;

                case 9:
                    funcionarioService.imprimirTotalSalarios();
                    break;

                case 10:
                    funcionarioService.imprimirSalariosMinimos();
                    break;

                case 11:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcoes != 11);

        scanner.close();
    }
}
