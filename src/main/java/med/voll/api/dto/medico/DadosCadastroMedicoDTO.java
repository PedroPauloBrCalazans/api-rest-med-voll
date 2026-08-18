package med.voll.api.dto.medico;

import med.voll.api.dto.endereco.DadosEnderecoDTO;

public record DadosCadastroMedicoDTO(String nome, String email, String crm, Especialidade especialidade, DadosEnderecoDTO endereco) {
}
