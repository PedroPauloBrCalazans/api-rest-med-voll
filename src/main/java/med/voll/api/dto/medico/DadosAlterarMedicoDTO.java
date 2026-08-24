package med.voll.api.dto.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.endereco.DadosEnderecoDTO;

public record DadosAlterarMedicoDTO(

        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEnderecoDTO endereco
) {
}
