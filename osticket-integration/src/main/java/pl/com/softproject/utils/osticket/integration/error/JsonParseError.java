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
 * Class JsonParseError
 *
 * @author Marcin Jasinski <mkjasinski@gmail.com>
 */
public class JsonParseError extends Exception {
    public JsonParseError(String message) {
        super(message);
    }
}
