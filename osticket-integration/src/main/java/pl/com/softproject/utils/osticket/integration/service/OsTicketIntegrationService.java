/**
 * This file is part of osTicketIntegration.
 *
 * (c) 2014 SoftProject
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.com.softproject.utils.osticket.integration.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import pl.com.softproject.utils.osticket.integration.error.JsonParseError;
import pl.com.softproject.utils.osticket.integration.error.OsTicketSystemError;
import pl.com.softproject.utils.osticket.integration.model.Ticket;

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
