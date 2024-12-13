package com.ntw.logistica_espacos.model.service;

import com.ntw.logistica_espacos.model.entity.Reserva;
import com.ntw.logistica_espacos.model.entity.Usuario;
import com.ntw.logistica_espacos.model.entity.enuns.StatusUsuario;
import com.ntw.logistica_espacos.model.repository.ReservaRepository;
import com.ntw.logistica_espacos.model.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario novoUsuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuarioExistente.setNome(novoUsuario.getNome());
        usuarioExistente.setEmail(novoUsuario.getEmail());
        usuarioExistente.setTipo(novoUsuario.getTipo());

        return usuarioRepository.save(usuarioExistente);
    }

    public void desativarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuario.setStatusUsuario(StatusUsuario.INATIVO);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }


    // Listar reservas associadas a um usuário
    public List<Reserva> listarReservasDoUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return reservaRepository.findByResponsavel(usuario);
    }
}

