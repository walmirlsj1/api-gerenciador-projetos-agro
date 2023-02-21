package br.com.limac.gerprojetos2.api.v1.controller;

import br.com.limac.gerprojetos2.api.v1.assembler.ClienteInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ClienteModelAssembler;
import br.com.limac.gerprojetos2.api.v1.model.ClienteModel;
import br.com.limac.gerprojetos2.api.v1.model.input.ClienteInput;
import br.com.limac.gerprojetos2.domain.model.Cliente;
import br.com.limac.gerprojetos2.domain.repository.ClienteRepository;
import br.com.limac.gerprojetos2.domain.service.CadastroClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteModelAssembler clienteModelAssembler;
    private final ClienteInputDisassembler clienteInputDisassembler;

    private final CadastroClienteService cadastroClienteService;

    @GetMapping
    public ResponseEntity<Object> listar(@RequestParam(name = "filter", required = false) String filter, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
//        List<Cliente> clientes = clienteRepository.findAll();

        if (filter == null || filter.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(clienteModelAssembler.toModelList(clienteRepository.findAll(pageable.getSort())));
        return ResponseEntity.status(HttpStatus.OK).body(clienteModelAssembler.toModelList(clienteRepository.findByNomeContainingIgnoreCase(filter, pageable)));
//        return ResponseEntity.ok(clienteModelAssembler.toModelList(clientes));
    }


    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> buscar(@PathVariable("clienteId") Long clienteId) {
        Cliente cliente = cadastroClienteService.buscarOuFalhar(clienteId);
        return ResponseEntity.ok(clienteModelAssembler.toModel(cliente));
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> atualizar(@PathVariable("clienteId") Long clienteId,
                                                  @RequestBody @Valid ClienteInput clienteInput) {
        Cliente clienteAtual = cadastroClienteService.buscarOuFalhar(clienteId);

        clienteInputDisassembler.copyToDomainObject(clienteInput, clienteAtual);
        clienteAtual = cadastroClienteService.atualizar(clienteAtual);

        return ResponseEntity.ok(clienteModelAssembler.toModel(clienteAtual));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteModel> adicionar(@RequestBody @Valid ClienteInput clienteInput) {
        Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);
        cliente = cadastroClienteService.inserir(cliente);


        return ResponseEntity.ok(clienteModelAssembler.toModel(cliente));
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("clienteId") Long clienteId) {
        cadastroClienteService.excluir(clienteId);
    }

}
