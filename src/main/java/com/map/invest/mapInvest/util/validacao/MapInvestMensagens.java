package com.map.invest.mapInvest.util.validacao;

public enum MapInvestMensagens {

    ERRO_VALIDACAO_COD_USUARIO_NAO_EXISTE("Código do usuário não existe na base de dados"),
    ERRO_VALIDACAO_COD_USUARIO_JA_EXISTE("Código do usuário já cadastrada na base de dados"),
    ERRO_VALIDACAO_EMAIL_JA_EXISTE("Email do usuário já cadastrada na base de dados"),
    ERRO_VALIDACAO_COD_PERFIL_NAO_EXISTE("Perfil não cadastrada na base de dados"),
    ERRO_VALIDACAO_LOGIN_JA_EXISTE("Login do usuário já cadastrada na base de dados"),
    ERRO_VALIDACAO_COD_PERFIL_OBRIGATORIO("Obrigatório informar o código do perfil"),
    ERRO_VALIDACAO_SEXO_INVALIDO("Erro ao validar o sexo do usuário. Os valores para este campo devem ser M: masculino, F: femininno e O: outros"),
    ERRO_VALIDACAO_NOME_OBRIGATORIO("Obrigatório informar o nome do usuário"),
    ERRO_VALIDACAO_SOBRENOME_OBRIGATORIO("Obrigatório informar o sobrenome do usuário"),
    ERRO_VALIDACAO_CPF_CNPJ_OBRIGATORIO("Obrigatório informar o CPF ou CNPJ do usuário"),
    ERRO_VALIDACAO_EMAIL_OBRIGATORIO("Obrigatório informar o email do usuário"),
    ERRO_VALIDACAO_LOGIN_OBRIGATORIO("Obrigatório informar o login do usuário"),
    ERRO_VALIDACAO_SENHA_OBRIGATORIO("Obrigatório informar a senha do usuário"),
    ERRO_VALIDACAO_DOCUMENTO_OBRIGATORIO("Obrigatório informar os dados do documento"),
    ERRO_VALIDACAO_TIPO_DOCUMENTO_OBRIGATORIO("Obrigatório informar o tipo de documento"),
    ERRO_VALIDACAO_NUMERO_DOCUMENTO_OBRIGATORIO("Obrigatório informar o número de documento"),
    ERRO_VALIDACAO_ENDERECO_OBRIGATORIO("Obrigatório informar o endereço"),
    ERRO_VALIDACAO_TIPO_ENDERECO_OBRIGATORIO("Obrigatório informar o tipo de endereço"),
    ERRO_VALIDACAO_CEP_OBRIGATORIO("Obrigatório informar um CEP valido para o endereço"),
    ERRO_VALIDACAO_RUA_OBRIGATORIO("Obrigatório informar a rua para endereço"),
    ERRO_VALIDACAO_NUMERO_OBRIGATORIO("Obrigatório informar o número para endereço"),
    ERRO_VALIDACAO_COMPLEMENTO_OBRIGATORIO("Obrigatório informar a complemento para endereço"),
    ERRO_VALIDACAO_CIDADE_OBRIGATORIO("Obrigatório informar a cidade para endereço"),
    ERRO_VALIDACAO_UF_OBRIGATORIO("Obrigatório informar a UF do endereço"),
    ERRO_VALIDACAO_TIPO_ENDERECO_INVALIDO("Erro ao validar o tipo de endereço"),
    ERRO_VALIDACAO_TIPO_DOCUMENTO_INVALIDO("Erro ao validar o tipo de documento"),
    ERRO_VALIDACAO_TELEFONE_OBRIGATORIO("Obrigatório informar um telefone"),
    ERRO_VALIDACAO_CODIGO_TELEFONE_TAMANHO_EXCEDIDO("Erro ao validar o codigo do telefone, tamanho excedido"),
    ERRO_VALIDACAO_TIPO_TELEFONE_OBRIGATORIO("Obrigatório informar o tipo de telefone"),
    ERRO_VALIDACAO_TIPO_TELEFONE_INVALIDO("Erro ao validar o tipo de telefone"),
    ERRO_VALIDACAO_NUMERO_TELEFONE_FIXO_INVALIDO("Erro ao validar o numero de telefone fixo"),
    ERRO_VALIDACAO_NUMERO_TELEFONE_CELULAR_INVALIDO("Erro ao validar o numero de telefone celular"),
    ERRO_VALIDACAO_ACESSO_OBRIGATORIO("Obrigatório informar o acesso"),
    ERRO_VALIDACAO_LOGIN_TAMANHO_MIN_INSUFICIENTE("Erro ao validar o login. Tamanho minimo insuficiente"),
    ERRO_VALIDACAO_LOGIN_TAMANHO_MAX_INSUFICIENTE("Erro ao validar o login. Tamanho maximo insuficiente"),
    ERRO_VALIDACAO_LOGIN_CARACTERES_INVALIDOS("Erro ao validar o login. Caracteres invalidos"),
    ERRO_VALIDACAO_SENHA_OBRIGATORIA("Obrigatório informar a senha"),
    ERRO_VALIDACAO_SENHA_TAMANHO_MIN_INSUFICIENTE("Erro ao validar a senha. Tamanho minimo insuficiente"),
    ERRO_VALIDACAO_SENHA_TAMANHO_MAX_INSUFICIENTE("Erro ao validar a senha. Tamanho maximo insuficiente"),
    ERRO_VALIDACAO_SENHA_CARACTERES_INVALIDOS("Erro ao validar a senha. Caracteres invalidos"),
    ERRO_VALIDACAO_CODIGO_TELEFONE_OBRIGATORIO("Obrigatório informar o codigo do telefone"),
    ERRO_VALIDACAO_COD_TELEFONE_NAO_EXISTE("Código do telefone não existe na base de dados"),
    ERRO_VALIDACAO_NUMERO_TELEFONE_OBRIGATORIO("Obrigatório informar o número do telefone"),
    ERRO_VALIDACAO_EMAIL_CARACTERES_INVALIDOS("Erro ao validar o email. Caracteres invalidos"),
    ERRO_VALIDACAO_NUM_DOCUMENTO_JA_EXISTE("Número do documento já cadastrada na base de dados"),
    ERRO_VALIDACAO_DATA_NASCIMENTO_OBRIGATORIO("Obrigatório informar a data de nascimento do usuario"),
    ERRO_VALIDACAO_SEXO_OBRIGATORIO("Obrigatório informar o sexo do usuario"),
    ERRO_VALIDACAO_NUMERO_DOCUMENTO_CPF_INVALIDO("CPF invalido, verifique o número do documento"),
    ERRO_VALIDACAO_NUMERO_DOCUMENTO_CNPJ_INVALIDO("CNPJ invalido, verifique o número do documento"),
    ERRO_VALIDACAO_BAIRRO_OBRIGATORIO("Obrigatório informar o bairro no endereço"),
    ERRO_FORMATO_CEP_INVALIDO("Informar somento os números do CEP sem o '-'"),
    ERRO_VALIDACAO_DADOS_PESSOA_FISICA_OBRIGATORIO("Obrigatório informar os dados dos documetos da pessoa fisica"),
    ERRO_VALIDACAO_NACIONALIDADE_OBRIGATORIO("Obrigatório informar a nacionalidade da pessoa fisica"),
    ERRO_VALIDACAO_NATURALIDADE_OBRIGATORIO("Obrigatório informar a naturalidade da pessoa fisica"),
    ERRO_VALIDACAO_DADOS_PESSOA_JURIDICA_OBRIGATORIO("Obrigatório informar os dados dos documetos da pessoa juridica"),
    ERRO_VALIDACAO_NOME_COMERCIAL_OBRIGATORIO("Obrigatório informar o nome comercial da pessoa juridica"),
    ERRO_VALIDACAO_DATA_CONSTITUICAO_OBRIGATORIO("Obrigatório informar a data de constituição da pessoa juridica"),
    ERRO_VALIDACAO_TIPO_INSCRICAO_OBRIGATORIO("Obrigatório informar o tipo de inscrição da pessoa juridica"),
    ERRO_VALIDACAO_NUMERO_INSCRICAO_OBRIGATORIO("Obrigatório informar o número de inscrição da pessoa juridica"),
    ERRO_VALIDACAO_NATUREZA_JURIDICA_OBRIGATORIO("Obrigatório informar a natureza da pessoa juridica"),
    ERRO_VALIDACAO_RAMO_ATIVIDADE_OBRIGATORIO("Obrigatório informar o ramo de atividade da pessoa juridica"),
    ERRO_VALIDACAO_OBJETIVO_SOCIAL_OBRIGATORIO("Obrigatório informar o objetivo social da pessoa juridica");


    private final String valor;

    private MapInvestMensagens(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static MapInvestMensagens findByCodigo(String valor) {
        for (MapInvestMensagens value : MapInvestMensagens.values()) {
            if (value.getValor().equalsIgnoreCase(valor.trim())) {
                return value;
            }
        }
        return null;
    }

}
