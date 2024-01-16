package cursomicroservicos.msavaliadorcredito.application;

import cursomicroservicos.msavaliadorcredito.domain.model.DadosCliente;
import cursomicroservicos.msavaliadorcredito.domain.model.SituacaoCliente;
import cursomicroservicos.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final ClienteResourceClient clientsClient;
    public SituacaoCliente obterSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> dadosClienteResponse = clientsClient.dadosCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .build();
    }
}
