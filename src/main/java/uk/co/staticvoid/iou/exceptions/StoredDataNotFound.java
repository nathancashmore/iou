package uk.co.staticvoid.iou.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class StoredDataNotFound extends RuntimeException {

    public StoredDataNotFound() {
        super();
    }

    public StoredDataNotFound(String message) {
        super(message);
    }

    public StoredDataNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
