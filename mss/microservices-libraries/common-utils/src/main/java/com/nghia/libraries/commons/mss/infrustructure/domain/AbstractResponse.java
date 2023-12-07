package com.nghia.libraries.commons.mss.infrustructure.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractResponse extends AbstractObject {
    private String code;
    private String message;
    private Object error;
    private Object body;

}