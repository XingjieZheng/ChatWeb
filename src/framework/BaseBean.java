package framework;

public class BaseBean extends BaseToStringInstance {
    /**
     * 任务执行成功(true)
     */
    public static final int STATUS_SUCCESS = 1;
    /**
     * 任务执行失败(false)
     */
    public static final int STATUS_FAILED = -1;

    /**
     * 执行消息，针对执行不同状态，返回的服务器提示消息
     **/
    private String msg;
    private int status;
    private boolean isLastPage = true;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public void setStatusSuccess() {
        this.status = STATUS_SUCCESS;
    }

    public void setStatusFailed() {
        this.status = STATUS_FAILED;
    }

}
