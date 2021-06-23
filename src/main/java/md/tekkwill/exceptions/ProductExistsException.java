package md.tekkwill.exceptions;

public class ProductExistsException extends ProductManagementRunTimeException {
    public ProductExistsException(String message) {
        super(message);
    }
}
