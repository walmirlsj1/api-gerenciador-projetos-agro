package br.com.limac.gerprojetos2.api.v1.controller;

import br.com.limac.gerprojetos2.api.v1.assembler.CaixaInputDisassembler;
import br.com.limac.gerprojetos2.api.v1.assembler.CaixaModelAssembler;
import br.com.limac.gerprojetos2.api.v1.model.input.CaixaInput;
import br.com.limac.gerprojetos2.domain.model.Caixa;
import br.com.limac.gerprojetos2.domain.model.Conta;
import br.com.limac.gerprojetos2.domain.model.ETipoCaixaLancamento;
import br.com.limac.gerprojetos2.domain.model.ProjetoLancamento;
import br.com.limac.gerprojetos2.domain.service.CadastroContaService;
import br.com.limac.gerprojetos2.domain.service.RegistroCaixaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bancos/{bancoId}/contas/{contaId}")
@AllArgsConstructor
public class FluxoCaixaController {
    private final CaixaModelAssembler caixaModelAssembler;
    private final CaixaInputDisassembler caixaInputDisassembler;

    private final RegistroCaixaService registroCaixaService;

    private final CadastroContaService cadastroContaService;

    @PutMapping("/depositar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registrarDepositoNaConta(@PathVariable("contaId") Long contaId, @RequestBody @Valid CaixaInput caixaInput) {

        Conta contaAtual = cadastroContaService.buscarOuFalhar(contaId);
        registroCaixaService.registrarCaixa(contaAtual, caixaInput.getValor(), ETipoCaixaLancamento.ENTRADA, caixaInput.getDescricao());
    }

    @PutMapping("/sacar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registrarSaqueDaConta(@PathVariable("contaId") Long contaId, @RequestBody @Valid CaixaInput caixaInput) {

        Conta contaAtual = cadastroContaService.buscarOuFalhar(contaId);
        registroCaixaService.registrarCaixa(contaAtual, caixaInput.getValor(), ETipoCaixaLancamento.SAIDA, caixaInput.getDescricao());
    }

}
