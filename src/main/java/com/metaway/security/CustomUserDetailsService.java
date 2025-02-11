package com.metaway.security;

import com.metaway.model.Usuario;
import com.metaway.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;



@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {


    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findUsuarioByCpf(cpf);
        if (user == null) {
            throw new UsernameNotFoundException("Não existe usuário com esse cpf " + cpf);
        }

        GrantedAuthority perfil = new SimpleGrantedAuthority(user.getPerfil().name());

        return new org.springframework.security.core.userdetails.User(
                user.getCpf(),
                user.getSenha(),
                Arrays.asList(perfil)
        );
    }
}
