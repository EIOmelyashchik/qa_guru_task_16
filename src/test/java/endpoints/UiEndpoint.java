package endpoints;

public enum UiEndpoint {
    CART("/cart"),
    FAVICON("/favicon.ico");

    private final String path;

    UiEndpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}