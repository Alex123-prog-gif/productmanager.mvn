package md.tekkwill.exceptions;

public class ProductManagementRunTimeException extends RuntimeException {
    public ProductManagementRunTimeException() {
        super();
    }

    public ProductManagementRunTimeException(String message) {
        super(message);
    }
}
