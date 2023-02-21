package br.com.limac.gerprojetos2.api.v1.controller;

import br.com.limac.gerprojetos2.api.v1.assembler.CaixaModelAssembler;
import br.com.limac.gerprojetos2.domain.model.Caixa;
import br.com.limac.gerprojetos2.domain.model.ProjetoLancamento;
import br.com.limac.gerprojetos2.domain.repository.CaixaRepository;
import br.com.limac.gerprojetos2.domain.service.CadastroLancamentoService;
import br.com.limac.gerprojetos2.domain.service.RegistraLancamentoCaixaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lancamentos/{lancamentoId}")
@AllArgsConstructor
public class FluxoLancamentoController {

    private final CaixaModelAssembler caixaModelAssembler;
    private final CadastroLancamentoService cadastroLancamentoService;
    private final RegistraLancamentoCaixaService registraLancamentoCaixaService;

    private final CaixaRepository caixaRepository;

    @GetMapping
    public List<Caixa> listar(){
        return caixaRepository.findAll();
    }

    @PutMapping("/depositar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void lancarNoCaixa(@PathVariable("lancamentoId") Long lancamentoId) {
        ProjetoLancamento projetoLancamentoAtual = cadastroLancamentoService.buscarOuFalhar(lancamentoId);

        registraLancamentoCaixaService.registrarLancamentoCaixa(projetoLancamentoAtual);
//        return ResponseEntity.accepted().body(caixaModelAssembler.toModel(caixa));
    }

    @PutMapping("/sacar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void estornarDoCaixa(@PathVariable("lancamentoId") Long lancamentoId) {
        ProjetoLancamento projetoLancamentoAtual = cadastroLancamentoService.buscarOuFalhar(lancamentoId);

        registraLancamentoCaixaService.estornarLancamentoCaixa(projetoLancamentoAtual);
    }

}
