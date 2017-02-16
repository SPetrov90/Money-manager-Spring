package ru.sergey90.util.exteption;

public class ErrorInfo {
    public final String url;
    public final String cause;
    public final String detail;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
//        this.detail = NestedExceptionUtils.buildMessage("", ex);
    }
}
