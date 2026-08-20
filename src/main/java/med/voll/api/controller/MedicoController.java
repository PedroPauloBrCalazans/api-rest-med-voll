package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroMedicoDTO dados) {  //eu recebo por parametro um DTO e converto para um objeto do tipo medico
        medicoRepository.save(new Medico(dados));
    }
}
