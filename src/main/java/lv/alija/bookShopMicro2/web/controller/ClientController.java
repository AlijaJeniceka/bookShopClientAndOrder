package lv.alija.bookShopMicro2.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lv.alija.bookShopMicro2.business.service.ClientService;
import lv.alija.bookShopMicro2.model.Client;
import lv.alija.bookShopMicro2.swagger.DescriptionVariables;
import lv.alija.bookShopMicro2.swagger.HTMLResponseMessages;
import org.kie.api.runtime.KieSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {DescriptionVariables.CLIENT})
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final KieSession kieSession;

    private final ClientService clientService;

    @GetMapping("/list")
    @ApiOperation(value = "Find list of all clients",
            notes = "Returns the entire list of clients",
            response = Client.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HTMLResponseMessages.HTTP_200, response = Client.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = HTMLResponseMessages.HTTP_404),
            @ApiResponse(code = 500, message = HTMLResponseMessages.HTTP_500)})
    public ResponseEntity<List<Client>> findAllClients() {
        log.info("Retrieve list of the books.");
        List<Client> clients = clientService.findAllClients();
        log.debug("Client list is found.Size: {}", clients::size);
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    @ApiOperation(value = "Saves the client in the database",
            notes = "If provide valid client, saves it",
            response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = HTMLResponseMessages.HTTP_201),
            @ApiResponse(code = 400, message = HTMLResponseMessages.HTTP_400),
            @ApiResponse(code = 404, message = HTMLResponseMessages.HTTP_404),
            @ApiResponse(code = 500, message = HTMLResponseMessages.HTTP_500)})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> saveClient(@Valid @RequestBody Client client, BindingResult bindingResult) throws Exception {
        log.info("Create new client by passing: {}", client);
        if (bindingResult.hasErrors()) {
            log.error("New client is not created: error {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }
        kieSession.insert(client);
        kieSession.fireAllRules();
        Client clientSaved = clientService.saveClient(client);
        log.debug("New client is created: {}", client);
        return new ResponseEntity<>(clientSaved, HttpStatus.CREATED);
    }


}
