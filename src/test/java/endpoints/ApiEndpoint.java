package endpoints;

public enum ApiEndpoint {
    MAIN("/"),
    LOGIN("/login"),
    ADD_TO_CART("/addproducttocart");

    private final String path;

    ApiEndpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String addPath(String additionalPath) {
        return path + additionalPath;
    }
}
