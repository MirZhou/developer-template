package cn.mir.common.utilities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 数据分页接口响应结果集
 * <p>Create time: 2019/4/30 4:46 PM</p>
 *
 * @author 周光兵
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponsePageResult<T> extends ResponseResult<PageResult<T>> {
    public ResponsePageResult() {
    }

    public ResponsePageResult(boolean success, String message) {
        super(success, message);
    }

    public ResponsePageResult(boolean success, int total, List<T> list) {
        super(success, new PageResult<>(total, list));
    }

    public ResponsePageResult(boolean success, int total, int pages, List<T> list) {
        super(success, new PageResult<>(total, pages, list));
    }

    @JsonIgnore
    public int getTotal() {
        return super.getData().getTotal();
    }

    @JsonIgnore
    public int getPages() {
        return super.getData().getPages();
    }

    @JsonIgnore
    public List<T> getList() {
        return super.getData().getList();
    }
}