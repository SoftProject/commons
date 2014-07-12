package pl.com.softproject.utils.osticket.integration.service;

import pl.com.softproject.utils.osticket.integration.error.JsonParseError;
import pl.com.softproject.utils.osticket.integration.error.OsTicketSystemError;
import pl.com.softproject.utils.osticket.integration.model.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Interface OsTicketIntegrationServiceInterface
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
public interface OsTicketIntegrationService {

    public String createTicket(Ticket ticket) throws JsonParseError, OsTicketSystemError;

    void appendFilesToTicket(Ticket ticket, List<File> files) throws IOException;
}
