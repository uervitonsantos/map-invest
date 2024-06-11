package com.map.invest.mapInvest.controllerImplements;

import com.map.invest.mapInvest.bean.EnderecoBean;
import com.map.invest.mapInvest.bean.UsuarioBean;
import com.map.invest.mapInvest.canonico.PessoaCanonico;
import com.map.invest.mapInvest.dto.PessoaDTO;
import com.map.invest.mapInvest.dtoFactory.PessoaDTOFactory;
import com.map.invest.mapInvest.entity.Endereco;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.filtroDTO.PessoaFiltroDTO;
import com.map.invest.mapInvest.resource.PessoaController;
import jakarta.ws.rs.BeanParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mapinvest/usuario")
public class PessoaControllerImpl implements PessoaController {
    private static final String PATH_ID = "/{pessoaID}";

    @Autowired
    private UsuarioBean usuarioBean;
    @Autowired
    private EnderecoBean enderecoBean;
    @Autowired
    private PessoaDTOFactory pessoaDTOFactory;

    @GetMapping(PATH_ID)
    @Override
    public ResponseEntity<PessoaDTO> getPessoa(@PathVariable(P_ID_PESSOA) Long pessoaID) {
        PessoaDTO dto = pessoaDTOFactory.pessoaDto(usuarioBean.buscaPessoa(pessoaID));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<PessoaDTO>> getPessoas(@BeanParam PessoaFiltroDTO filtro) {
        FiltroWrapper wrapper = filtro.filtroWrapper();
        List<PessoaCanonico> usuarios = usuarioBean.buscaPessoas(wrapper);
        return ResponseEntity.ok(pessoaDTOFactory.pessoasDto(usuarios));
    }

    @PostMapping
    @Override
    public ResponseEntity<PessoaDTO> criaPessoa(@RequestBody PessoaDTO dto) {
        PessoaCanonico canonico = pessoaDTOFactory.pessoaCanonico(dto);
        Endereco enderecoResult = enderecoBean.buscarEnderecoPorCep(dto.getEndereco().getCep());
        PessoaCanonico pessoaSalva = usuarioBean.criaPessoa(canonico, enderecoResult);
        return criaResponse(pessoaSalva);
    }

    @PutMapping(PATH_ID)
    @Override
    public ResponseEntity atualizaPessoa(@PathVariable(P_ID_PESSOA) Long pessoaID, @RequestBody PessoaDTO dto) {
        dto.setPessoaID(pessoaID);
        PessoaCanonico canonico = pessoaDTOFactory.pessoaCanonico(dto);
        Endereco enderecoResult = enderecoBean.buscarEnderecoPorCep(dto.getEndereco().getCep());
        PessoaCanonico usuarioAtualizada = usuarioBean.editaPessoa(canonico, enderecoResult);
        return criaResponse(usuarioAtualizada);
    }

    private ResponseEntity criaResponse(@RequestBody PessoaCanonico pessoaSalva) {
        PessoaDTO pessoaDto = pessoaDTOFactory.pessoaDto(usuarioBean.buscaPessoa(pessoaSalva.getPessoaID()));
        return ResponseEntity.ok().body(pessoaDto);
    }

    @DeleteMapping(PATH_ID)
    @Override
    public ResponseEntity removePessoa(@PathVariable(P_ID_PESSOA) Long pessoaID) {
        usuarioBean.removePessoa(pessoaID);
        return ResponseEntity.noContent().build();
    }
}
