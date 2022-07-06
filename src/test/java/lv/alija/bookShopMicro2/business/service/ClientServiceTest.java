package lv.alija.bookShopMicro2.business.service;

import lv.alija.bookShopMicro2.business.mapper.ClientMapper;
import lv.alija.bookShopMicro2.business.repository.ClientRepository;
import lv.alija.bookShopMicro2.business.repository.model.ClientDAO;
import lv.alija.bookShopMicro2.business.service.impl.ClientServiceImpl;
import lv.alija.bookShopMicro2.exception.OrderClientControllerException;
import lv.alija.bookShopMicro2.model.Client;
import lv.alija.bookShopMicro2.model.enums.ClientTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ClientServiceTest {

    @InjectMocks
    ClientServiceImpl clientService;
    @Spy
    ClientRepository clientRepository;
    @Spy
    ClientMapper clientMapper;

    private Client client;
    private ClientDAO clientDAO;
    private List<Client> clientList;
    private List<ClientDAO> clientDAOList;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @BeforeEach
    void init(){
        clientDAO = createClientDAO();
        client = createClient();
        clientDAOList = createClientListDAO();
        clientList = createClientList();
    }
    @Test
    void findAllClients() {
        when(clientRepository.findAll()).thenReturn(clientDAOList);
        when(clientMapper.clientDAOToClient(clientDAO)).thenReturn(client);
        List<Client> clients = clientService.findAllClients();
        assertEquals(2, clients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void findAllClients_InvalidTest(){
        when(clientRepository.findAll()).thenReturn(Collections.emptyList())
                        .thenThrow(new OrderClientControllerException(HttpStatus.NOT_FOUND, "Client list is empty"));
        assertThrows(OrderClientControllerException.class, () -> clientService.findAllClients());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void findClientById() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(clientDAO));
        when(clientMapper.clientDAOToClient(clientDAO)).thenReturn(client);
        Optional<Client> clientById = clientService.clientById(client.getId());
        assertEquals(client.getId(), clientById.get().getId());
        assertEquals(client.getAge(), clientById.get().getAge());
        assertEquals(client.getDiscount(), clientById.get().getDiscount());
        assertEquals(client.getType(), clientById.get().getType());
        verify(clientRepository, times(1)).findById(anyLong());
    }

    @Test
    void findBookById_idNegativeOrNull_InvalidTest() {
        assertThrows(OrderClientControllerException.class, () -> clientService.clientById(-1L));
        verify(clientRepository, times(0)).findById(-1L);
    }

    @Test
    void findBookById_idPositiveButNotFound_InvalidTest() {
        assertThrows(OrderClientControllerException.class, () -> clientService.clientById(5L));
        verify(clientRepository, times(1)).findById(5L);
    }
    @Test
    void saveClient() throws Exception {
        when(clientRepository.save(clientDAO)).thenReturn(clientDAO);
        when(clientMapper.clientDAOToClient(clientDAO)).thenReturn(client);
        when(clientMapper.clientToClientDAO(client)).thenReturn(clientDAO);
        Client savedClient = clientService.saveClient(client);
        assertEquals(savedClient, client);
        verify(clientRepository, times(1)).save(clientDAO);
    }

    @Test
    void saveClient_InvalidTest(){
        when(clientRepository.save(clientDAO)).thenThrow(new IllegalArgumentException());
        when(clientMapper.clientToClientDAO(client)).thenReturn(clientDAO);
        assertThrows(IllegalArgumentException.class, () -> clientService.saveClient(client));
        verify(clientRepository, times(1)).save(clientDAO);
    }

    private List<Client> createClientList(){
        List<Client> clientList = new ArrayList<>();
        Client client1 = createClient();
        Client client2 = createClient();
        clientList.add(client1);
        clientList.add(client2);
        return clientList;
    }
    private Client createClient(){
        Client client = new Client();
        client.setId(1L);
        client.setAge(25L);
        client.setType(ClientTypes.PERSON);
        client.setDiscount(5L);
        client.setDocumentForDiscount(true);
        return client;
    }
    private List<ClientDAO> createClientListDAO(){
        List<ClientDAO> clientDAOList = new ArrayList<>();
        ClientDAO clientDAO1 = createClientDAO();
        ClientDAO clientDAO2 = createClientDAO();
        clientDAOList.add(clientDAO1);
        clientDAOList.add(clientDAO2);
        return clientDAOList;
    }
    private ClientDAO createClientDAO(){
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.setId(1L);
        clientDAO.setAge(25L);
        clientDAO.setType(ClientTypes.PERSON);
        clientDAO.setDiscount(5L);
        clientDAO.setDocumentForDiscount(true);
        return clientDAO;
    }
}