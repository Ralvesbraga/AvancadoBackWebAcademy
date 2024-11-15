package br.ufac.sgcmapi.controller.dto;

public record ProfissionalDto(
    Long id,
    String nome,
    String email,
    String registroConselho,
    String telefone,
    Long especialidade_id,
    String especialidade_nome,
    Long unidade_id,
    String unidade_nome
) {
} 
