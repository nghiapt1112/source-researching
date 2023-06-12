package com.demo.Icommerce.infrastructure;

import com.demo.Icommerce.infrastructure.util.JsonUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;

public abstract class Request {

    protected Map<String, Object> body;

    public Map<String, Object> getBody() {
        return body;
    }

    public Object getFirstBodyElement() {
        if (CollectionUtils.isEmpty(this.body)) {
            return null;
        }
        String firstKey = this.body.keySet().stream().findFirst().get();
        // FIXME: only exec for the first element of the Map, not supported yet multiple elements inside a request.
        return this.body.get(firstKey);
    }

    public <T> T getFirstBodyElement(Class<T> type) {
        String firstKey = this.body.keySet().stream().findFirst().get();
        // FIXME: only exec for the first element of the Map, not supported yet multiple elements inside a request.
        return type.cast(this.body.get(firstKey));
    }
}

class Input extends Request {
}

class Output extends Request {
    private int status;

    public int getStatus() {
        return status;
    }

    public Map<String, Object> expectResponse() {
        return body;
    }

    public String bodyToJson() {
        return JsonUtils.toJson(this.body);
    }
}
