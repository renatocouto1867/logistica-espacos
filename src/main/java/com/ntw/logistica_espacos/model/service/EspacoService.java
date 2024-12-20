package com.ntw.logistica_espacos.model.service;

import com.ntw.logistica_espacos.exception.ConflictException;
import com.ntw.logistica_espacos.exception.NotFoundException;
import com.ntw.logistica_espacos.model.entity.Espaco;
import com.ntw.logistica_espacos.model.entity.enuns.StatusEspaco;
import com.ntw.logistica_espacos.model.repository.EspacoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class EspacoService {
    @Autowired
    private EspacoRepository espacoRepository;

    public Espaco criarEspaco(Espaco espaco) {
        if (espacoRepository.findByNome(espaco.getNome()).isPresent()) {
            //Aqui eu verifico se jé existe espaço com o mesmo nome
            throw new ConflictException("Espaço já cadastrado.");
        }
        espaco.setDataCadastro(LocalDate.now());
        return espacoRepository.save(espaco);
    }

    public Espaco atualizarEspaco(Long id, Espaco novoEspaco) {
        Espaco espaco = espacoRepository.findById(id).orElseThrow(() -> new NotFoundException("Espaço não encontrado."));
        espaco.setNome(novoEspaco.getNome());
        espaco.setDescricao(novoEspaco.getDescricao());
        return espacoRepository.save(espaco);
    }

    public void desativarEspaco(Long id) {
        Espaco espaco = espacoRepository.findById(id).orElseThrow(() -> new NotFoundException("Espaço não encontrado."));
        espaco.setStatus(StatusEspaco.INATIVO);
        espacoRepository.save(espaco);
    }

    public void rativarEspaco(Long id) {
        Espaco espaco = espacoRepository.findById(id).orElseThrow(() -> new NotFoundException("Espaço não encontrado."));
        espaco.setStatus(StatusEspaco.ATIVO);
        espacoRepository.save(espaco);
    }

    public List<Espaco> listarTodos() {
        return espacoRepository.findAll();
    }

    public Espaco buscarPorId(Long id) {
        return espacoRepository.findById(id).orElseThrow(() -> new NotFoundException("Espaço não encontrado."));
    }
}



