package pl.com.softproject.utils.freshmail.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.com.softproject.utils.freshmail.dto.response.BasicError;
import pl.com.softproject.utils.freshmail.dto.response.ErrorResponse;
import pl.com.softproject.utils.freshmail.exception.common.AccessDeniedForControllerActionException;
import pl.com.softproject.utils.freshmail.exception.common.AuthorizationFailedException;
import pl.com.softproject.utils.freshmail.exception.common.ControllerActionNotFoundException;
import pl.com.softproject.utils.freshmail.exception.common.IncorrectEmailAddressException;
import pl.com.softproject.utils.freshmail.exception.common.InternalException;
import pl.com.softproject.utils.freshmail.exception.common.ProtocolNotSupportedException;
import pl.com.softproject.utils.freshmail.exception.common.RequestMethodNotSupportedException;
import pl.com.softproject.utils.freshmail.exception.common.UnknownException;
import pl.com.softproject.utils.freshmail.exception.mail.AttachmentFileNotFoundException;
import pl.com.softproject.utils.freshmail.exception.mail.IncorrectAddressReplayToException;
import pl.com.softproject.utils.freshmail.exception.mail.IncorrectAttachmentUrlException;
import pl.com.softproject.utils.freshmail.exception.mail.IncorrectDomainForRebrandingException;
import pl.com.softproject.utils.freshmail.exception.mail.IncorrectEmailContentException;
import pl.com.softproject.utils.freshmail.exception.mail.IncorrectEmailSubjectException;
import pl.com.softproject.utils.freshmail.exception.mail.IncorrectSenderAddressException;
import pl.com.softproject.utils.freshmail.exception.mail.IncorrectSenderNameException;
import pl.com.softproject.utils.freshmail.exception.mail.IncorrectTagException;
import pl.com.softproject.utils.freshmail.exception.mail.MailNotSentException;
import pl.com.softproject.utils.freshmail.exception.mail.MailNotSentSubscriberLockedException;
import pl.com.softproject.utils.freshmail.exception.mail.OutOfEmailLimitException;
import pl.com.softproject.utils.freshmail.exception.mail.OutOfSizeAttachmentFileException;
import pl.com.softproject.utils.freshmail.exception.mail.UnsupportedEncodingException;
import pl.com.softproject.utils.freshmail.exception.subscriber.IncorrectAdditionalFieldException;
import pl.com.softproject.utils.freshmail.exception.subscriber.IncorrectListHashException;
import pl.com.softproject.utils.freshmail.exception.subscriber.IncorrectSubscriberStateException;
import pl.com.softproject.utils.freshmail.exception.subscriber.SubscriberExistsOnListException;

import java.io.IOException;

/**
 * Class ClientUtil
 *
 * @author Marcin Jasiński {@literal <mkjasinski@gmail.com>}
 */
public class ClientUtil {

    private ClientUtil() {
    }

    public static ErrorResponse errorResponse(int responseStatus, String stringResponse,
                                              String errorLabel) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        if (responseStatus == 200) {
            return null;
        }

        ErrorResponse errorResponse = objectMapper.readValue(stringResponse, ErrorResponse.class);

        if (errorResponse.getStatus().equalsIgnoreCase(errorLabel)
            && errorResponse.getErrors() == null || errorResponse.getErrors().size() == 0) {

            throw new UnknownException(0, "Unknown error!");
        }

        return errorResponse;
    }

    public static void catchStandardException(int responseStatus, String stringResponse,
                                              String errorLabel) throws IOException {

        ErrorResponse errorResponse = errorResponse(responseStatus, stringResponse, errorLabel);

        if (errorResponse == null) {
            return;
        }

        BasicError error = errorResponse.getErrors().get(0);

        Integer code = error.getCode();
        String message = error.getMessage();

        switch (code) {
            case 500:
                throw new InternalException(code, message);
            case 1000:
                throw new AuthorizationFailedException(code, message);
            case 1001:
                throw new ControllerActionNotFoundException(code, message);
            case 1002:
                throw new ProtocolNotSupportedException(code, message);
            case 1003:
                throw new RequestMethodNotSupportedException(code, message);
            case 1004:
                throw new AccessDeniedForControllerActionException(code, message);
        }
    }

    public static void catchSubscriberAddException(int responseStatus, String stringResponse,
                                                   String errorLabel) throws IOException {

        ErrorResponse errorResponse = errorResponse(responseStatus, stringResponse, errorLabel);

        if (errorResponse == null) {
            return;
        }

        BasicError error = errorResponse.getErrors().get(0);

        Integer code = error.getCode();
        String message = error.getMessage();

        switch (code) {
            case 1301:
                throw new IncorrectEmailAddressException(code, message);
            case 1302:
                throw new IncorrectListHashException(code, message);
            case 1303:
                throw new IncorrectAdditionalFieldException(code, message);
            case 1304:
                throw new SubscriberExistsOnListException(code, message);
            case 1305:
                throw new IncorrectSubscriberStateException(code, message);
        }
    }

    public static void catchMailException(int responseStatus, String stringResponse,
                                          String errorLabel) throws IOException {

        ErrorResponse errorResponse = errorResponse(responseStatus, stringResponse, errorLabel);

        if (errorResponse == null) {
            return;
        }

        BasicError error = errorResponse.getErrors().get(0);

        Integer code = error.getCode();
        String message = error.getMessage();

        switch (code) {
            case 1201:
                throw new IncorrectEmailAddressException(code, message);
            case 1202:
                throw new IncorrectEmailSubjectException(code, message);
            case 1203:
                throw new IncorrectEmailContentException(code, message);
            case 1204:
                throw new IncorrectAddressReplayToException(code, message);
            case 1205:
                throw new IncorrectSenderAddressException(code, message);
            case 1206:
                throw new IncorrectSenderNameException(code, message);
            case 1207:
                throw new OutOfEmailLimitException(code, message);
            case 1208:
                throw new UnsupportedEncodingException(code, message);
            case 1209:
                throw new IncorrectAttachmentUrlException(code, message);
            case 1210:
                throw new AttachmentFileNotFoundException(code, message);
            case 1211:
                throw new OutOfSizeAttachmentFileException(code, message);
            case 1212:
                throw new IncorrectDomainForRebrandingException(code, message);
            case 1213:
                throw new IncorrectTagException(code, message);
            case 1250:
                throw new MailNotSentException(code, message);
            case 1251:
                throw new MailNotSentSubscriberLockedException(code, message);
        }
    }
}
