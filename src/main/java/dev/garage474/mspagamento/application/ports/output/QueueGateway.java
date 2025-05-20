package dev.garage474.mspagamento.application.ports.output;

public interface QueueGateway {

    void enviarMensagem(String message);

}
