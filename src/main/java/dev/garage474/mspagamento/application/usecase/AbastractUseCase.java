package dev.garage474.mspagamento.application.usecase;

import dev.garage474.mspagamento.adapter.dto.BaseDTO;

/**
 * Classe abstrata que representa um caso de uso na aplicação.
 */
public abstract class AbastractUseCase<DTO extends BaseDTO> {

    protected static final String REQUEST_NOT_NULL = "Request não pode ser nulo";
    protected DTO request;

    protected abstract void executa();

    /**
     * Metodo que executa a lógica de negócio do caso de uso.
     */
    protected final void executaInternal() {
        validaRequest();
        try {
            executa();
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao executar o caso de uso: " + e.getMessage());
        }
    }

    public void setRequest(DTO request) {
        if (request == null) {
            throw new IllegalArgumentException(REQUEST_NOT_NULL);
        }
        this.request = request;
    }

    private void validaRequest() {
        if (request == null) {
            throw new IllegalArgumentException(REQUEST_NOT_NULL);
        }
    }

}
