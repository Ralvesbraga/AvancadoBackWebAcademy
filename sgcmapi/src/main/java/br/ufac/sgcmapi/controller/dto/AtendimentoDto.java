package br.ufac.sgcmapi.controller.dto;

public record AtendimentoDto(
    Long id,
    String data,
    String hora,
    String status,
    Long profissional_id,
    String profissional_nome,
    Long paciente_id,
    String paciente_nome,
    Long convenio_id,
    String convenio_nome,
    String unidade_nome
) {
    
}
