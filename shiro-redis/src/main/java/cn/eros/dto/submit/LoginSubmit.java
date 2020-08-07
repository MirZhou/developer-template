package cn.eros.dto.submit;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>create time：2020-08-05 18:58
 *
 * @author Eros
 */
@Data
public class LoginSubmit implements Serializable {
    private static final long serialVersionUID = -462860437536171112L;

    private String username;
    private String password;

    public LoginSubmit() {
    }

    public LoginSubmit(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
