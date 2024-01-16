package cursomicroservicos.msavaliadorcredito.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("avaliacao-credito")
public class AvaliadorDeCreditoController {
    @GetMapping
    public String status() {
        return "ok";
    }
}
