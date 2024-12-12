package com.ntw.logistica_espacos.model.entity.enuns;

public enum TipoUsuario {
    ADMINISTRADOR,  // Usuário com permissões para gerenciar todo o sistema.
    GESTOR,         // Usuário com permissões para gerenciar espaços e reservas.
    USUARIO_PADRAO  // Usuário comum, com permissões limitadas.
}
