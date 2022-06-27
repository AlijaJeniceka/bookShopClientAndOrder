package lv.alija.bookShopMicro2.business.mapper;

import lv.alija.bookShopMicro2.business.repository.model.ClientDAO;
import lv.alija.bookShopMicro2.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDAO clientToClientDAO(Client client);

    Client clientDAOToClient(ClientDAO clientDAO);
}
