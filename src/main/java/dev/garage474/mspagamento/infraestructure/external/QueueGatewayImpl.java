package dev.garage474.mspagamento.infraestructure.external;

import dev.garage474.mspagamento.application.ports.output.QueueGateway;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.List;

@Service
public class QueueGatewayImpl implements QueueGateway {

    private final SqsClient sqsClient;
    private static final String SQL_URL = "https://sqs.us-east-1.amazonaws.com/260761541415/ms-pagseg-psql-queue";

    public QueueGatewayImpl(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    @Override
    public void enviarMensagem(String message) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(SQL_URL)
                .messageBody(message)
                .build();

        sqsClient.sendMessage(request);
    }

    public String recebeMensagem() {
        var request = ReceiveMessageRequest.builder()
                .queueUrl(SQL_URL)
                .maxNumberOfMessages(1)
                .waitTimeSeconds(10)
                .build();

        List<Message> messages = sqsClient.receiveMessage(request).messages();
        return messages.getFirst().body();

        // Exclui a mensagem após processar
//        sqsClient.deleteMessage(builder -> builder
//                .queueUrl(SQL_URL)
//                .receiptHandle(messages.getFirst().receiptHandle()));

    }

}
