package br.com.limac.gerprojetos2.api.v1.controller;

import br.com.limac.gerprojetos2.api.v1.assembler.ProjetoInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ProjetoModelAssembler;
import br.com.limac.gerprojetos2.api.v1.model.ProjetoModel;
import br.com.limac.gerprojetos2.api.v1.model.input.ProjetoInput;
import br.com.limac.gerprojetos2.domain.model.Cliente;
import br.com.limac.gerprojetos2.domain.model.ETipoProjetoLancamento;
import br.com.limac.gerprojetos2.domain.model.Projeto;
import br.com.limac.gerprojetos2.domain.model.ProjetoLancamento;
import br.com.limac.gerprojetos2.domain.repository.ProjetoRepository;
import br.com.limac.gerprojetos2.domain.service.CadastroProjetoService;
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
@RequestMapping("/api/v1/projetos")
@AllArgsConstructor
public class ProjetoController {

    private final ProjetoRepository projetoRepository;
    private final ProjetoModelAssembler projetoModelAssembler;
    private final ProjetoInputDisassembler projetoInputDisassembler;
    private final CadastroProjetoService cadastroProjetoService;

//    @GetMapping
//    public ResponseEntity<List<ProjetoModel>> listar() {
//        List<Projeto> projetos = projetoRepository.findAll();
//        return ResponseEntity.ok(projetoModelAssembler.toModelList(projetos));
//    }

    @GetMapping
    public ResponseEntity<Object> listar(@RequestParam(name = "filter", required = false) String filter, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
//        List<Cliente> clientes = clienteRepository.findAll();

        if (filter == null || filter.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(projetoModelAssembler.toModelList(projetoRepository.findAll(pageable.getSort())));
        return ResponseEntity.status(HttpStatus.OK).body(projetoModelAssembler.toModelList(projetoRepository.findByNomeContainingIgnoreCase(filter, pageable)));

//        throw new RuntimeException("NOT IMPLEMENTED");
    }

    @GetMapping("/{projetoId}")
    public ResponseEntity<ProjetoModel> buscar(@PathVariable("projetoId") Long projetoId) {
        Projeto projeto = cadastroProjetoService.buscarOuFalhar(projetoId);
        return ResponseEntity.ok(projetoModelAssembler.toModel(projeto));
    }

    @PutMapping("/{projetoId}")
    public ResponseEntity<ProjetoModel> atualizar(@PathVariable("projetoId") Long projetoId,
                                                  @RequestBody @Valid ProjetoInput projetoInput) {

        Projeto projetoAtual = cadastroProjetoService.buscarOuFalhar(projetoId);
        projetoInputDisassembler.copyToDomainObject(projetoInput, projetoAtual);
        projetoAtual = cadastroProjetoService.atualizar(projetoAtual);

        return ResponseEntity.ok(projetoModelAssembler.toModel(projetoAtual));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjetoModel> adicionar(@RequestBody @Valid ProjetoInput projetoInput) {

        Projeto projeto = projetoInputDisassembler.toDomainObject(projetoInput);

        Cliente cliente = new Cliente();
        cliente.setId(projeto.getCliente().getId());

        projeto.setCliente(cliente);
        projeto = cadastroProjetoService.inserir(projeto);

        return ResponseEntity.ok(projetoModelAssembler.toModel(projeto));
    }


    @PutMapping("/{projetoId}/receberProjeto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void receberProjeto(@PathVariable("projetoId") Long projetoId) {

        /**
         * @TODO
         * Projeto pode ser pago parcelado..,
         *
         */
        throw new RuntimeException("Não implementado");
//        Projeto projetoAtual = cadastroProjetoService.buscarOuFalhar(projetoId);

//        ProjetoLancamento lancamento = new ProjetoLancamento();
//        lancamento.setProjeto(projetoAtual);
//        lancamento.setTipoLancamento(ETipoProjetoLancamento.ENTRADA);
//        lancamento.setPago(true);
//        lancamento.setDescricao("Recebimento do valor integral do projeto");


//        return ResponseEntity.ok(projetoModelAssembler.toModel(projetoAtual));
    }

    @DeleteMapping("/{projetoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("projetoId") Long clienteId) {
        cadastroProjetoService.excluir(clienteId);
    }
}
