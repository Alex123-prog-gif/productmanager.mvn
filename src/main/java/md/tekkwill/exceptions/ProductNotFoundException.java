package md.tekkwill.exceptions;

public class ProductNotFoundException extends ProductManagementRunTimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
