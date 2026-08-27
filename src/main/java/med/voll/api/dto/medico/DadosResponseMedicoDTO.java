package med.voll.api.dto.medico;

import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;

public record DadosResponseMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade, Endereco endereco) {

    public DadosResponseMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(),
                medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
