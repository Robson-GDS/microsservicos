package cursomicroservicos.msavaliadorcredito.application.ex;

public class ErroSolicitacaoCartaoException extends RuntimeException {
    public ErroSolicitacaoCartaoException(String message) {
        super(message);
    }
}
