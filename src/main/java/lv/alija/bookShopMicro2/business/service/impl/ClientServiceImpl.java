package lv.alija.bookShopMicro2.business.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lv.alija.bookShopMicro2.business.mapper.ClientMapper;
import lv.alija.bookShopMicro2.business.repository.ClientRepository;
import lv.alija.bookShopMicro2.business.repository.model.ClientDAO;
import lv.alija.bookShopMicro2.business.service.ClientService;
import lv.alija.bookShopMicro2.exception.OrderClientControllerException;
import lv.alija.bookShopMicro2.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    public List<Client> findAllClients() {
        List<ClientDAO> clientDAO = clientRepository.findAll();
        log.info("Get client list. Size is : {}", clientDAO::size);
        if (clientDAO.isEmpty()) {
            log.warn("Books list is not found. ");
            throw new OrderClientControllerException(HttpStatus.NOT_FOUND, "Client list is empty");
        }
        return clientDAO.stream().map(clientMapper::clientDAOToClient)
                .collect(Collectors.toList());
    }

    @Override
    public Client saveClient(Client client) throws Exception {
        ClientDAO clientDAO = clientMapper.clientToClientDAO(client);
        ClientDAO clientSaved = clientRepository.save(clientDAO);
        log.info("Client is created: {}", () -> clientSaved);
        return clientMapper.clientDAOToClient(clientSaved);
    }

    @Override
    public Optional<Client> clientById(Long id) {
        if (id <= 0) {
            log.warn("Client id is not null or negative number. Insert only positive numbers. ");
            throw new OrderClientControllerException(HttpStatus.BAD_REQUEST, "Id should be bigger then null");
        }
        Optional<Client> clientById = clientRepository.findById(id)
                .flatMap(bookDAO -> Optional.ofNullable(clientMapper.clientDAOToClient(bookDAO)));
        if (!clientById.isPresent()) {
            log.warn("Client with id {} is not found. ", id);
            throw new OrderClientControllerException(HttpStatus.NOT_FOUND, "Client with this id is not found");
        }
        log.info("Client with id {} is {}", id, clientById);
        return clientById;
    }
}


