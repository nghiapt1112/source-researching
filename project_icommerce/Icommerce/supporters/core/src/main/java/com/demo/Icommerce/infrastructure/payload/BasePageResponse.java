package com.demo.Icommerce.infrastructure.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class BasePageResponse<E extends BaseEntity, O extends BaseResponse> extends BaseResponse {
    protected List<O> details;
    protected Integer page;
    protected Integer size;
    private Long total;

    public BasePageResponse(Integer page, Integer size, Long total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public Long getTotal() {
        try {
            return size == 0 ? 1 : (long) Math.ceil((double) total / (double) size);
        } catch (Exception e) {
            return 0L;
        }
    }

    public abstract void parse(List<E> entities);
}
