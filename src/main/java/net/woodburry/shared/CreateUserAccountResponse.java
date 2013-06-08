package net.woodburry.shared;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gamma_000
 * Date: 6/8/13
 * Time: 12:32 AM
 */
public class CreateUserAccountResponse implements Serializable {
    private boolean success;
    private String reason;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
