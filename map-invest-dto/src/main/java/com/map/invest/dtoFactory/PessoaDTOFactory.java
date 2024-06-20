package com.map.invest.dtoFactory;

import com.map.invest.canonico.PessoaCanonico;
import com.map.invest.dto.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PessoaDTOFactory {

    @Autowired
    private TelefoneDTOFactory telefoneDTOFactory;
    @Autowired
    private AcessoDTOFactory acessoDTOFactory;
    @Autowired
    private DocumentoPrincipalDTOFactory documentoPrincipalDTOFactory;
    @Autowired
    private EnderecoDTOFactory enderecoDTOFactory;
    @Autowired
    private AuditoriaDTOFactory auditoriaDTOFactory;

    public PessoaDTO pessoaDto(PessoaCanonico pessoa) {
        return Optional.ofNullable(pessoa).map(canonico -> {
            return PessoaDTO.builder()
                    .pessoaID(canonico.getPessoaID())
                    .nome(canonico.getNome())
                    .email(canonico.getEmail())
                    .ativo(canonico.getAtivo())
                    .documentoPrincipal(documentoPrincipalDTOFactory.documentoDto(canonico.getDocumentoPrincipal()))
                    .endereco(enderecoDTOFactory.enderecoDto(canonico.getEndereco()))
                    .telefones(telefoneDTOFactory.telefonesDTO(canonico.getTelefones()))
                    .acesso(acessoDTOFactory.acessoDto(canonico.getAcesso()))
                    .auditoria(auditoriaDTOFactory.auditoriasDto(canonico.getAuditoria()))
                    .build();
        }).orElse(null);
    }

    public List<PessoaDTO> pessoasDto(List<PessoaCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> pessoaDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }

    public PessoaCanonico pessoaCanonico(PessoaDTO dto) {
        return Optional.ofNullable(dto).map(entidade -> {
            return PessoaCanonico.builder()
                    .pessoaID(entidade.getPessoaID())
                    .nome(entidade.getNome())
                    .email(entidade.getEmail())
                    .documentoPrincipal(documentoPrincipalDTOFactory.documentoCanonico(entidade.getDocumentoPrincipal()))
                    .endereco(enderecoDTOFactory.enderecoCanonico(entidade.getEndereco()))
                    .telefones(telefoneDTOFactory.telefonesCanonico(entidade.getTelefones()))
                    .acesso(acessoDTOFactory.acessoCanonico(entidade.getAcesso()))
                    .build();
        }).orElse(null);
    }
}
