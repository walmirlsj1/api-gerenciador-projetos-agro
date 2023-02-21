package br.com.limac.gerprojetos2.api.v1.controller;

import br.com.limac.gerprojetos2.api.v1.assembler.ContaInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ContaModelAssembler;
import br.com.limac.gerprojetos2.api.v1.model.ContaModel;
import br.com.limac.gerprojetos2.api.v1.model.input.ContaInput;
import br.com.limac.gerprojetos2.domain.model.Banco;
import br.com.limac.gerprojetos2.domain.model.Conta;
import br.com.limac.gerprojetos2.domain.repository.ContaRepository;
import br.com.limac.gerprojetos2.domain.service.CadastroContaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contas")
@AllArgsConstructor
public class ContaController {

    private final ContaRepository contaRepository;
    private final ContaModelAssembler contaModelAssembler;
    private final ContaInputDisassembler contaInputDisassembler;

    private final CadastroContaService cadastroContaService;

    @GetMapping
    public ResponseEntity<Object> listar(@RequestParam(name = "filter", required = false) String filter, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

//        if (filter == null || filter.isEmpty())
        return ResponseEntity.status(HttpStatus.OK).body(contaRepository.findAll(pageable));
//        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findByNomeContainingIgnoreCase(filter, pageable));
    }


    @GetMapping("/{contaId}")
    public ResponseEntity<ContaModel> buscar(@PathVariable("contaId") Long contaId) {
        Conta conta = cadastroContaService.buscarOuFalhar(contaId);
        return ResponseEntity.ok(contaModelAssembler.toModel(conta));
    }

    @PutMapping("/{contaId}")
    public ResponseEntity<ContaModel> atualizar(@PathVariable("contaId") Long contaId,
                                                @RequestBody @Valid ContaInput contaInput) {
        Conta contaAtual = cadastroContaService.buscarOuFalhar(contaId);

        contaInputDisassembler.copyToDomainObject(contaInput, contaAtual);
        contaAtual = cadastroContaService.atualizar(contaAtual);

        return ResponseEntity.ok(contaModelAssembler.toModel(contaAtual));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContaModel> adicionar(@RequestBody @Valid ContaInput contaInput) {
        Conta conta = contaInputDisassembler.toDomainObject(contaInput);
//        Banco banco = new Banco();
//        banco.setId(bancoId);
//        conta.setBanco(banco);
        conta = cadastroContaService.inserir(conta);


        return ResponseEntity.ok(contaModelAssembler.toModel(conta));
    }


}
