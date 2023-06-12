package com.demo.Icommerce.infrastructure.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends BaseObject {
    private String code;
    private String message;
    private String key;
    private List<String> messages;

    public void updateMessage(String toString) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(toString);
    }
}
