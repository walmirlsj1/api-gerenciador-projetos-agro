package br.com.limac.gerprojetos2.api.v1.controller;

import br.com.limac.gerprojetos2.api.v1.assembler.CaixaInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.CaixaModelAssembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ContaInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ContaModelAssembler;
import br.com.limac.gerprojetos2.api.v1.model.CaixaModel;
import br.com.limac.gerprojetos2.api.v1.model.ContaModel;
import br.com.limac.gerprojetos2.api.v1.model.input.CaixaInput;
import br.com.limac.gerprojetos2.api.v1.model.input.ContaInput;
import br.com.limac.gerprojetos2.domain.model.Banco;
import br.com.limac.gerprojetos2.domain.model.Caixa;
import br.com.limac.gerprojetos2.domain.model.Conta;
import br.com.limac.gerprojetos2.domain.repository.CaixaRepository;
import br.com.limac.gerprojetos2.domain.repository.ContaRepository;
import br.com.limac.gerprojetos2.domain.service.CadastroContaService;
import br.com.limac.gerprojetos2.domain.service.RegistroCaixaService;
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
@RequestMapping("/api/v1/caixas")
@AllArgsConstructor
public class CaixaController {

    private final CaixaRepository caixaRepository;
    private final CaixaModelAssembler caixaModelAssembler;
    private final CaixaInputDisassembler caixaInputDisassembler;

    private final RegistroCaixaService registroCaixaService;


    @GetMapping
    public ResponseEntity<Object> listar(@RequestParam(name = "filter", required = false) String filter, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
//        if (filter == null || filter.isEmpty())
        return ResponseEntity.status(HttpStatus.OK).body(caixaModelAssembler.toPageModel(caixaRepository.findAll(pageable)));
//        return ResponseEntity.status(HttpStatus.OK).body(caixaRepository.findByNomeContainingIgnoreCase(filter, pageable));

//        throw new RuntimeException("NOT IMPLEMENTED");
    }

//    @GetMapping
//    public ResponseEntity<List<CaixaModel>> listar() {
//        List<Caixa> caixas = caixaRepository.findAll();
//        return ResponseEntity.ok(caixaModelAssembler.toModelList(caixas));
//    }


    @GetMapping("/{caixaId}")
    public ResponseEntity<CaixaModel> buscar(@PathVariable("caixaId") Long caixaId) {
        Caixa caixa = registroCaixaService.buscarOuFalhar(caixaId);
        return ResponseEntity.ok(caixaModelAssembler.toModel(caixa));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CaixaModel> adicionar(@PathVariable("caixaId") Long caixaId, @RequestBody @Valid CaixaInput caixaInput) {
        Caixa caixa = caixaInputDisassembler.toDomainObject(caixaInput);

        return ResponseEntity.ok(caixaModelAssembler.toModel(caixa));
    }


}
