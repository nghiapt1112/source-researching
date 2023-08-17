package com.example.filex.excel2;

import com.example.filex.excel.BaseDTO;
import com.example.filex.excel.ExcelError;
import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class BaseAction {
    protected BaseAction next;

    // dùng ở ngoài chỗ init. => register Next Action.
    public BaseAction next(BaseAction nextAction) {
        // log
        // do something
        if (this.next == null) { // chưa có action tiếp theo cài đặt trc đó -> lấy input.
            this.next = nextAction;
        } else { // đã có action tiếp theo -> nhét vào cái +2
            this.next.next(nextAction);
        }
        return this;
    }

    // True stop
    public boolean execute(List<? extends BaseDTO> records, List<ExcelError> excelErrors) {
        if (next == null) {
            return false;
        }
        if (!CollectionUtils.isEmpty(records)) {
            System.out.println("Type of DTO:" + records.get(0).getClass());
        }
        return next.execute(records, excelErrors);
    }

}
