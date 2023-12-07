package com.nghia.libraries.commons.mss.infrustructure.domain;

import com.nghia.libraries.commons.mss.utils.JsonUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomAbstractObjectMessageConverter extends AbstractHttpMessageConverter<Object> {

    public static final String SUCCESS = "Success";
    public static final String SUCCESS_CODE = "0";

    public static final String FATAL_ERROR = "Fatal error: ";
    private static final String ERROR_STATUS_KEY = "status";
    private static final String ERROR_MSG_KEY = "error";

    public CustomAbstractObjectMessageConverter() {
        super(new MediaType("application", "json"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return JsonUtils.OBJECT_MAPPER.readValue(inputMessage.getBody(), clazz);
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        AbstractResponse response;
        if (object instanceof AbstractResponse) {
            // in case: handled in ExceptionHandler.
            response = (AbstractResponse) object;
        } else if (object instanceof Map) {
            // System Error
            LinkedHashMap error = ((LinkedHashMap) object);
            response = AbstractResponse.builder()
                    .code(String.valueOf(error.get(ERROR_STATUS_KEY)))
                    .message(FATAL_ERROR.concat(String.valueOf(((LinkedHashMap) object).get(ERROR_MSG_KEY))))
                    .build();
        } else {
            // Success request
            response = AbstractResponse.builder()
                    .code(SUCCESS_CODE)
                    .message(SUCCESS)
                    .body(object)
                    .build();
        }
        outputMessage.getBody().write(JsonUtils.toBytes(response));
    }
}
