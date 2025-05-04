package br.com.senai.projetoSustentavel.service;
import br.com.senai.projetoSustentavel.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ResponsavelDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public ResponsavelDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Responsável não encontrado: " + username));
    }
}
