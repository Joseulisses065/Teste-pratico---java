package utils;

import dominio.entidades.Funcionario;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FuncaoSimples {
    private static final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private static final DecimalFormat nf = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());

    static {
        nf.applyPattern("#,##0.00");
    }

    public static void inserirFuncionario(Funcionario funcionario, List<Funcionario> lista){
        lista.add(funcionario);
        System.out.println(String.format("Funcionario %s adicionado", funcionario.getNome()));
    }

    public static void removerFuncionario(String nome, List<Funcionario> lista){
        lista.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public static void listarFuncionarios(List<Funcionario> funcionarios){
        funcionarios.forEach(FuncaoSimples::imprimirFuncionario);
    }

    public static void imprimirFuncionario(Funcionario funcionario) {
        System.out.println(String.format("""
                Dados do funcionário
                ---------------------------
                Nome: %s
                Data de Nascimento: %s
                Salário: %s
                Função: %s
                """, funcionario.getNome(), formatDate(funcionario.getDataDeNascimento()),
                nf.format(funcionario.getSalario()), funcionario.getFuncao()));
    }

    private static String formatDate(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return df.format(date);
    }
}
