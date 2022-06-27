package lv.alija.bookShopMicro2.business.service;

import lv.alija.bookShopMicro2.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAllClients();


    Client saveClient(Client client) throws Exception;
}

