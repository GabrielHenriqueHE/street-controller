package io.github.gabrielhenriquehe.streetcontroller.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11}");
    private static final Integer IDADE_MINIMA = 18;

    public static boolean validarCpf(String cpf) {
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {
            return false;
        }

        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }

        if (primeiroDigitoVerificador != (cpf.charAt(9) - '0')) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador >= 10) {
            segundoDigitoVerificador = 0;
        }

        // Verificação do segundo dígito
        return segundoDigitoVerificador == (cpf.charAt(10) - '0');
    }

    public static boolean validarNome(String nome) {
        if (nome.isEmpty()) return false;
        if (nome.isBlank()) return false;
        if (nome.length() < 3) return false;
        if (nome.trim().length() < 3) return false;

        return true;
    }

    public static boolean validarIdade(Date dataNascimento) {
        if (dataNascimento == null) {
            return false;
        }

        Calendar dataAtual = Calendar.getInstance();
        Calendar dataNasc = Calendar.getInstance();
        dataNasc.setTime(dataNascimento);

        if (dataNasc.after(dataAtual)) {
            return false;
        }

        int anoAtual = dataAtual.get(Calendar.YEAR);
        int anoNascimento = dataNasc.get(Calendar.YEAR);
        int idade = anoAtual - anoNascimento;

        if (dataNasc.get(Calendar.MONTH) > dataAtual.get(Calendar.MONTH) ||
                (dataNasc.get(Calendar.MONTH) == dataAtual.get(Calendar.MONTH) &&
                        dataNasc.get(Calendar.DAY_OF_MONTH) > dataAtual.get(Calendar.DAY_OF_MONTH))) {
            idade--;
        }

        return idade >= IDADE_MINIMA;
    }
}
