package com.map.invest.mapInvest.resourceImplements;

import com.map.invest.mapInvest.bean.UsuarioBean;
import com.map.invest.mapInvest.canonico.UsuarioCanonico;
import com.map.invest.mapInvest.dto.UsuarioDTO;
import com.map.invest.mapInvest.dtoFactory.UsuarioDTOFactory;
import com.map.invest.mapInvest.filtro.FiltroWrapper;
import com.map.invest.mapInvest.filtroDTO.UsuarioFiltroDTO;
import com.map.invest.mapInvest.resource.UsuarioResource;
import jakarta.ws.rs.BeanParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mapinvest/usuario")
public class UsuarioResourceImpl implements UsuarioResource {
    private static final String PATH_ID = "/{usuarioID}";
    @Autowired
    private UsuarioBean usuarioBean;
    @Autowired
    private UsuarioDTOFactory usuarioDTOFactory;

    @GetMapping(PATH_ID)
    @Override
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable(P_ID_USUARIO) Long usuarioID) {
        UsuarioDTO dto = usuarioDTOFactory.usuarioDto(usuarioBean.buscaUsuario(usuarioID));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UsuarioDTO>> getUsuarios(@BeanParam UsuarioFiltroDTO filtro) {
        FiltroWrapper wrapper = filtro.filtroWrapper();
        List<UsuarioCanonico> usuarios = usuarioBean.buscaUsuarios(wrapper);
        return ResponseEntity.ok(usuarioDTOFactory.usuariosDto(usuarios));
    }

    @PostMapping
    @Override
    public ResponseEntity<UsuarioDTO> criaUsuario(@RequestBody UsuarioDTO dto) {
        UsuarioCanonico canonico = usuarioDTOFactory.usuarioCanonico(dto);
        UsuarioCanonico usuarioSalva = usuarioBean.criaUsuario(canonico);
        return criaResponse(usuarioSalva);
    }

    @PutMapping(PATH_ID)
    @Override
    public ResponseEntity atualizaUsuario(@PathVariable(P_ID_USUARIO) Long usuarioID, @RequestBody UsuarioDTO dto) {
        dto.setUsuarioID(usuarioID);
        UsuarioCanonico canonico = usuarioDTOFactory.usuarioCanonico(dto);
        UsuarioCanonico usuarioAtualizada = usuarioBean.editaUsuario(canonico);
        return criaResponse(usuarioAtualizada);
    }

    private ResponseEntity criaResponse(@RequestBody UsuarioCanonico usuarioSalva) {
        UsuarioDTO usuarioDto = usuarioDTOFactory.usuarioDto(usuarioBean.buscaUsuario(usuarioSalva.getUsuarioID()));
        return ResponseEntity.ok().body(usuarioDto);
    }

    @DeleteMapping(PATH_ID)
    @Override
    public ResponseEntity removeUsuario(@PathVariable(P_ID_USUARIO) Long usuarioID) {
        usuarioBean.removeUsuario(usuarioID);
        return ResponseEntity.noContent().build();
    }
}
