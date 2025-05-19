package dev.garage474.mspagamento.infraestructure.external;

import dev.garage474.mspagamento.application.ports.output.QueueGateway;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class QueueGatewayImpl implements QueueGateway {

    private final SqsClient sqsClient;
    private static final String SQL_URL = "https://sqs.us-east-1.amazonaws.com/260761541415/ms-pagseg-psql-queue";

    public QueueGatewayImpl(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    @Override
    public void sendMessage(String message) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(SQL_URL)
                .messageBody(message)
                .build();

        sqsClient.sendMessage(request);
    }

}
