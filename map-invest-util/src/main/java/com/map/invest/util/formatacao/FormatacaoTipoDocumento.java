package com.map.invest.util.formatacao;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

@Component
public class FormatacaoTipoDocumento {

    public String formatarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return null;
        }
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");
        // Insere máscara de formatação
        return String.format("%s.%s.%s-%s",
                cpf.substring(0, 3),
                cpf.substring(3, 6),
                cpf.substring(6, 9),
                cpf.substring(9, 11));
    }

    public String formatarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            return null;
        }
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");
        // Insere máscara de formatação
        return String.format("%s.%s.%s/%s-%s",
                cnpj.substring(0, 2),
                cnpj.substring(2, 5),
                cnpj.substring(5, 8),
                cnpj.substring(8, 12),
                cnpj.substring(12, 14));
    }

    public boolean validarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty() || cpf.length() != 11) {
            return false; // CPF inválido por tamanho ou vazio
        }
        // Remova caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");
        // Cálculo do primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDV = soma % 11;
        if (primeiroDV == 0 || primeiroDV == 1) {
            primeiroDV = 0;
        } else {
            primeiroDV = 11 - primeiroDV;
        }
        // Cálculo do segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDV = soma % 11;
        if (segundoDV == 0 || segundoDV == 1) {
            segundoDV = 0;
        } else {
            segundoDV = 11 - segundoDV;
        }
        // Validação dos dígitos verificadores
        return (cpf.charAt(9) == (char) (primeiroDV + '0') &&
                cpf.charAt(10) == (char) (segundoDV + '0'));
    }

    public static boolean validarCNPJ(String cnpj) {
        // Validação de sequências repetidas
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
                (cnpj.length() != 14)) {
            return false;
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            // Cálculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            // Cálculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
                return true;
            else
                return false;
        } catch (InputMismatchException erro) {
            return false;
        }
    }
}
