package cursomicroservicos.msavaliadorcredito.application;

import cursomicroservicos.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import cursomicroservicos.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import cursomicroservicos.msavaliadorcredito.domain.model.CartaoCliente;
import cursomicroservicos.msavaliadorcredito.domain.model.DadosCliente;
import cursomicroservicos.msavaliadorcredito.domain.model.SituacaoCliente;
import cursomicroservicos.msavaliadorcredito.infra.clients.CartoesResourceClient;
import cursomicroservicos.msavaliadorcredito.infra.clients.ClienteResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final ClienteResourceClient clientsClient;
    private final CartoesResourceClient cartoesClient;
    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
        ResponseEntity<DadosCliente> dadosClienteResponse = clientsClient.dadosCliente(cpf);
        ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .cartoes(cartoesResponse.getBody())
                .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }
}
