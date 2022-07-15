package one.desafioDIO.designpatterns.service;

import one.desafioDIO.designpatterns.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com/ws")
public interface ViaCepService {

    @GetMapping("/{cep}/jason/")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
