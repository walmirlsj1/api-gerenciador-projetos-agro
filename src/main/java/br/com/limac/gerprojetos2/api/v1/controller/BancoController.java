package br.com.limac.gerprojetos2.api.v1.controller;

import br.com.limac.gerprojetos2.api.v1.assembler.BancoInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.BancoModelAssembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ContaInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ContaModelAssembler;
import br.com.limac.gerprojetos2.api.v1.model.BancoModel;
import br.com.limac.gerprojetos2.api.v1.model.ContaModel;
import br.com.limac.gerprojetos2.api.v1.model.input.BancoInput;
import br.com.limac.gerprojetos2.api.v1.model.input.ContaInput;
import br.com.limac.gerprojetos2.domain.model.Banco;
import br.com.limac.gerprojetos2.domain.model.Conta;
import br.com.limac.gerprojetos2.domain.repository.BancoRepository;
import br.com.limac.gerprojetos2.domain.repository.ContaRepository;
import br.com.limac.gerprojetos2.domain.service.CadastroBancoService;
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
@RequestMapping("/api/v1/bancos")
@AllArgsConstructor
public class BancoController {

    private final BancoRepository bancoRepository;
    private final BancoModelAssembler bancoModelAssembler;
    private final BancoInputDisassembler bancoInputDisassembler;

    private final CadastroBancoService cadastroBancoService;

    //    @GetMapping
//    public ResponseEntity<List<BancoModel>> listar() {
//        List<Banco> bancos = bancoRepository.findAll();
//        return ResponseEntity.ok(bancoModelAssembler.toModelList(bancos));
//    }
    @GetMapping
    public ResponseEntity<Object> listar(@RequestParam(name = "filter", required = false) String filter, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        if (filter == null || filter.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(bancoRepository.findAll(pageable));
        return ResponseEntity.status(HttpStatus.OK).body(bancoRepository.findByNomeContainingIgnoreCase(filter, pageable));
    }

    @GetMapping("/{bancoId}")
    public ResponseEntity<BancoModel> buscar(@PathVariable("bancoId") Long bancoId) {
        Banco banco = cadastroBancoService.buscarOuFalhar(bancoId);
        return ResponseEntity.ok(bancoModelAssembler.toModel(banco));
    }

    @PutMapping("/{bancoId}")
    public ResponseEntity<BancoModel> atualizar(@PathVariable("bancoId") Long bancoId,
                                                @RequestBody @Valid BancoInput bancoInput) {
        Banco bancoAtual = cadastroBancoService.buscarOuFalhar(bancoId);

        bancoInputDisassembler.copyToDomainObject(bancoInput, bancoAtual);
        bancoAtual = cadastroBancoService.atualizar(bancoAtual);

        return ResponseEntity.ok(bancoModelAssembler.toModel(bancoAtual));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BancoModel> adicionar(@RequestBody @Valid BancoInput bancoInput) {
        Banco banco = bancoInputDisassembler.toDomainObject(bancoInput);
        banco = cadastroBancoService.inserir(banco);


        return ResponseEntity.ok(bancoModelAssembler.toModel(banco));
    }


}
