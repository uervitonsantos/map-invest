package com.map.invest.mapInvest.util.validacao;

public enum CodigoUsuario {

    ERRO_VALIDACAO_COD_USUARIO_NAO_EXISTE("Código do usuário não existe na base de dados."),
    ERRO_VALIDACAO_COD_USUARIO_JA_EXISTE("Código do usuário já cadastrada na base de dados."),
    ERRO_VALIDACAO_EMAIL_JA_EXISTE("Email do usuário já cadastrada na base de dados."),
    ERRO_VALIDACAO_EMAIL_NAO_EXISTE("Email do usuário não existe na base de dados."),
    ERRO_VALIDACAO_LOGIN_JA_EXISTE("Login do usuário já cadastrada na base de dados."),
    ERRO_VALIDACAO_COD_PERFIL_OBRIGATORIO("Obrigatório informar o código do perfil."),
    ERRO_VALIDACAO_NOME_OBRIGATORIO("Obrigatório informar o nome do usuário."),
    ERRO_VALIDACAO_SOBRENOME_OBRIGATORIO("Obrigatório informar o sobrenome do usuário."),
    ERRO_VALIDACAO_CPF_CNPJ_OBRIGATORIO("Obrigatório informar o CPF ou CNPJ do usuário."),
    ERRO_VALIDACAO_EMAIL_OBRIGATORIO("Obrigatório informar o email do usuário."),
    ERRO_VALIDACAO_LOGIN_OBRIGATORIO("Obrigatório informar o login do usuário."),
    ERRO_VALIDACAO_SENHA_OBRIGATORIO("Obrigatório informar a senha do usuário."),

    ;

    private final String valor;

    private CodigoUsuario(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
