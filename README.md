![uml](https://github.com/uervitonsantos/map-invest/assets/15036785/2fbfc388-02ad-423f-ac51-b834ee8dca4c)


The following is a JSON document relating the table data:
```JSON

{
    "usuarioID": 3,
    "nome": "Maria",
    "sobreNome": "da Silva",
    "dataNascimento": "1990-09-20",
    "sexo": "F",
    "email": "maria@example.com",
    "documento": {
        "documentoID": 2,
        "tipoDocumento": "CPF",
        "numeroDocumento": "123456789"
    },
    "endereco": {
        "enderecoID": 2,
        "tipoEndereco": "COMERCIAL",
        "cep": "04578-000",
        "rua": "Rua Comercial",
        "numero": "100",
        "complemento": "Sala 200",
        "cidade": "São Paulo",
        "uf": "SP"
    },
    "telefones": [
        {
            "telefoneID": 2,
            "codigo": "55",
            "tipoTelefone": "RESIDENCIAL",
            "numeroTelefone": "1122334455"
        },
        {
            "telefoneID": 3,
            "codigo": "55",
            "tipoTelefone": "CELULAR",
            "numeroTelefone": "11987654321"
        }
    ],
    "acesso": {
        "acessoID": 3,
        "perfilID": 2,
        "login": "maria_silva",
        "senha": "$2a$10$u7HWcrGjN9stBvNTkbBpGeFlQlVFCCiaGazHd6oUjNUegfp1kG4le",
        "perfis": {
            "perfilID": 2,
            "codPerfil": "BSC",
            "nomePerfil": "BASICO",
            "descricao": "Perfil basico do sistema",
            "permissaoTelas": [
                {
                    "permissaoTelaID": 4,
                    "nomePermissao": "CONSULTAR",
                    "descricao": "Permissão para consultar dados na tela"
                },
                {
                    "permissaoTelaID": 5,
                    "nomePermissao": "IMPRIMIR",
                    "descricao": "Permissão para imprimir dados na tela"
                },
                {
                    "permissaoTelaID": 6,
                    "nomePermissao": "EXPORTAR",
                    "descricao": "Permissão para exportar dados na tela"
                },
                {
                    "permissaoTelaID": 16,
                    "nomePermissao": "ACESSAR",
                    "descricao": "Permissão para acessar a tela"
                }
            ]
        }
    }
}

```
