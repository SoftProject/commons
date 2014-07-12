package pl.com.softproject.utils.osticket.integration.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jackson.map.ObjectMapper;
import pl.com.softproject.utils.osticket.integration.config.Config;
import pl.com.softproject.utils.osticket.integration.encoder.FileEncoder;
import pl.com.softproject.utils.osticket.integration.error.JsonParseError;
import pl.com.softproject.utils.osticket.integration.error.OsTicketSystemError;
import pl.com.softproject.utils.osticket.integration.model.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Class OsTicketIntegrationService
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 * @author Adrian Lapierre <adrian@soft-project.pl>
 */
public class OsTicketIntegrationServiceImpl implements OsTicketIntegrationService {

    private Config config;
    private FileEncoder fileEncoder;

    public OsTicketIntegrationServiceImpl() {

    }

    public OsTicketIntegrationServiceImpl(Config config, FileEncoder fileEncoder) {

        this.config = config;
        this.fileEncoder = fileEncoder;
    }

    @Override
    public void appendFilesToTicket(Ticket ticket, List<File> files) throws IOException {

        List<Map<String, String>> encodedFiles = fileEncoder.encode(files);
        ticket.getAttachments().addAll(encodedFiles);
    }

    @Override
    public String createTicket(Ticket ticket) throws JsonParseError, OsTicketSystemError {

        checkIsProperlyInitialized();

        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(ticket);
        } catch (IOException exception) {
            throw new JsonParseError(exception.getMessage());
        }

        Client client = Client.create();
        client.setFollowRedirects(false);

        WebResource webResource = client.resource(config.getUrl());

        ClientResponse response = webResource
                .header("X-API-Key", config.getKey())
                .type("application/json")
                .post(ClientResponse.class, json);

        if (response.getStatus() < 200 || response.getStatus() >= 300) {
            throw new OsTicketSystemError(
                    "osTicket system error with number: " + response.getStatus() + " and message: " + response.getStatusInfo()
            );
        }

        return response.getEntity(String.class);
    }

    private void checkIsProperlyInitialized() throws IllegalStateException {

        if (config == null) {
            throw new IllegalStateException("configuration not found, use setConfig() method");
        }
        if (fileEncoder == null) {
            throw new IllegalStateException("fileEncoder not found, use setFileEncoder() method");
        }
    }

    public void setConfig(Config config) {

        this.config = config;
    }

    public void setFileEncoder(FileEncoder fileEncoder) {

        this.fileEncoder = fileEncoder;
    }
}
