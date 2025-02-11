package com.metaway.converter;

import com.metaway.api.dto.request.UsuarioRequest;
import com.metaway.api.dto.response.UsuarioResponse;
import com.metaway.model.Usuario;
import com.metaway.model.enums.Perfil;
import com.metaway.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UsuarioConverter {

    public Usuario toModel(UsuarioRequest request){

        return Usuario.builder()
                .nome(request.getNome())
                .cpf(Utils.removeMascaraCpf(request.getCpf()))
                .perfil(Perfil.valueOf(request.getPerfil()))
                .senha(request.getSenha())
                .build();

    }


    public UsuarioResponse toResponse(Usuario usuario){

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .cpf(usuario.getCpf())
                .perfil(usuario.getPerfil().name())
                .senha(usuario.getSenha())
                .build();
    }

    public List<UsuarioResponse> ToListResponse(List<Usuario> listEntity){
        return listEntity.stream().map(this::toResponse).toList();
    }
}
