/**
 * This file is part of osTicketIntegration.
 *
 * (c) 2014 SoftProject
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package pl.com.softproject.utils.osticket.integration.error;

/**
 * Class OsTicketSystemException
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
public class OsTicketSystemError extends Exception {
    public OsTicketSystemError(String message) {
        super(message);
    }
}
