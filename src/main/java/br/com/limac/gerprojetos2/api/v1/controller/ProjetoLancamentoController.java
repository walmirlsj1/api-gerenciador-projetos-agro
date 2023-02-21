package br.com.limac.gerprojetos2.api.v1.controller;

import br.com.limac.gerprojetos2.api.v1.assembler.ProjetoInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ProjetoLancamentoInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ProjetoLancamentoModelAssembler;
import br.com.limac.gerprojetos2.api.v1.assembler.ProjetoModelAssembler;
import br.com.limac.gerprojetos2.api.v1.model.ProjetoLancamentoModel;
import br.com.limac.gerprojetos2.api.v1.model.ProjetoModel;
import br.com.limac.gerprojetos2.api.v1.model.input.ProjetoInput;
import br.com.limac.gerprojetos2.api.v1.model.input.ProjetoLancamentoInput;
import br.com.limac.gerprojetos2.domain.model.Cliente;
import br.com.limac.gerprojetos2.domain.model.Projeto;
import br.com.limac.gerprojetos2.domain.model.ProjetoLancamento;
import br.com.limac.gerprojetos2.domain.repository.ProjetoLancamentoRepository;
import br.com.limac.gerprojetos2.domain.repository.ProjetoRepository;
import br.com.limac.gerprojetos2.domain.service.CadastroLancamentoService;
import br.com.limac.gerprojetos2.domain.service.CadastroProjetoService;
import br.com.limac.gerprojetos2.domain.service.RegistraLancamentoCaixaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes/{clienteId}/projetos/{projetoId}/lancamentos")
@AllArgsConstructor
public class ProjetoLancamentoController {

    private final ProjetoLancamentoRepository projetoLancamentoRepository;
    private final ProjetoLancamentoModelAssembler projetoLancamentoModelAssembler;
    private final ProjetoLancamentoInputDisassembler projetoLancamentoInputDisassembler;
    private final CadastroLancamentoService cadastroLancamentoService;
    private final RegistraLancamentoCaixaService registraLancamentoCaixaService;

    @GetMapping
    public ResponseEntity<List<ProjetoLancamentoModel>> listar() {
        List<ProjetoLancamento> projetoLancamentos = projetoLancamentoRepository.findAll();
        return ResponseEntity.ok(projetoLancamentoModelAssembler.toModelList(projetoLancamentos));
    }


    @GetMapping("/{lancamentoId}")
    public ResponseEntity<ProjetoLancamentoModel> buscar(@PathVariable("lancamentoId") Long lancamentoId) {
        ProjetoLancamento projetoLancamento = cadastroLancamentoService.buscarOuFalhar(lancamentoId);
        return ResponseEntity.ok(projetoLancamentoModelAssembler.toModel(projetoLancamento));
    }

    @PutMapping("/{lancamentoId}")
    public ResponseEntity<ProjetoLancamentoModel> atualizar(@PathVariable("lancamentoId") Long lancamentoId,
                                                            @RequestBody @Valid ProjetoLancamentoInput projetoLancamentoInput) {
        ProjetoLancamento projetoLancamentoAtual = cadastroLancamentoService.buscarOuFalhar(lancamentoId);

        projetoLancamentoInputDisassembler.copyToDomainObject(projetoLancamentoInput, projetoLancamentoAtual);
        projetoLancamentoAtual = cadastroLancamentoService.atualizar(projetoLancamentoAtual);

        return ResponseEntity.ok(projetoLancamentoModelAssembler.toModel(projetoLancamentoAtual));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjetoLancamentoModel> adicionar(@PathVariable("projetoId") Long projetoId, @RequestBody @Valid ProjetoLancamentoInput projetoLancamentoInput) {

//        System.out.println("*******************\n" + projetoInput.getConta().getId() + "\n*****************");

        ProjetoLancamento projetoLancamento = projetoLancamentoInputDisassembler.toDomainObject(projetoLancamentoInput);

//        System.out.println("*******************\n" + projetoInput.getConta().getId() + "\n*****************");
        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        projetoLancamento.setProjeto(projeto);
        projetoLancamento = cadastroLancamentoService.inserir(projetoLancamento);

        if (projetoLancamento.isPago())
            registraLancamentoCaixaService.registrarLancamentoCaixa(projetoLancamento);

        return ResponseEntity.ok(projetoLancamentoModelAssembler.toModel(projetoLancamento));
    }


}
