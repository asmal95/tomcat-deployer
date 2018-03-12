package ru.javazen.deployer.deploy.tomcat;

public class TomcatManagerResponse {
    private int statusCode;

    private String reasonPhrase;

    private String httpResponseBody;

    public TomcatManagerResponse() {
        // no op
    }

    public int getStatusCode() {
        return statusCode;
    }

    public TomcatManagerResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public TomcatManagerResponse setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    public String getHttpResponseBody() {
        return httpResponseBody;
    }

    public TomcatManagerResponse setHttpResponseBody(String httpResponseBody) {
        this.httpResponseBody = httpResponseBody;
        return this;
    }
}
