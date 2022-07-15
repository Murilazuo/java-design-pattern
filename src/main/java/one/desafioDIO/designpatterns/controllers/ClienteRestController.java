package one.desafioDIO.designpatterns.controllers;

import one.desafioDIO.designpatterns.model.Cliente;
import one.desafioDIO.designpatterns.service.ClientService;
import org.aspectj.apache.bcel.generic.InstructionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.init.RepositoriesPopulatedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        return ResponseEntity.ok(clientService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(clientService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente){
        clientService.inserir(cliente);
        return  ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        clientService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        clientService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
