package one.desafioDIO.designpatterns.service.impl;

import one.desafioDIO.designpatterns.model.ClientRepository;
import one.desafioDIO.designpatterns.model.Cliente;
import one.desafioDIO.designpatterns.model.Endereco;
import one.desafioDIO.designpatterns.model.EnderecoRepository;
import one.desafioDIO.designpatterns.service.ClientService;
import one.desafioDIO.designpatterns.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClienteServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clientRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }



    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clientRepository.findById(id);
        if(clienteBd.isPresent()){
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
            clientRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();

        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        cliente.setEndereco(endereco);

        clientRepository.save(cliente);
    }
}
