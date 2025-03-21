package busynessmanager.managers;

import busynessmanager.UI_Constants.UI;
import busynessmanager.product.Product;
import static busynessmanager.UI_Constants.Constants.INVALID_NAME;
import static busynessmanager.UI_Constants.Constants.NEWLINE;
import static busynessmanager.UI_Constants.Constants.SRM_ID_QUERY_FORMAT;
import static busynessmanager.UI_Constants.Constants.SRM_NAME_QUERY_FORMAT;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * SearchManager is used to query through the InventoryManager and return different results based on the given queries
 */
public class SearchManager {
    private final InventoryManager inventory;

    /**
     * Constructor
     * @param inventory Pre-existing inventory to be used for instantiation
     */
    public SearchManager(InventoryManager inventory) {
        this.inventory = inventory;
    }

    /**
     * Returns ID of the given product Name
     * @param name Name of the product to query
     * @return The key(ID) value of the given name (returns "INVALID_NAME" if an invalid name is provided)
     */
    public String searchByName(String name) {
        HashMap<String, Product> currentProductList = this.inventory.returnProductList();
        Set<Map.Entry<String, Product>> mapSet = currentProductList.entrySet();

        for (Map.Entry<String, Product> entry : mapSet) {
            if (entry.getValue().getName().equals(name)) {
                String id = entry.getKey();
                UI.printFormattedMessage(SRM_ID_QUERY_FORMAT + NEWLINE, name, id);
                return id;
            }
        }

        // Possible avenue for error (Invalid name provided)
        return INVALID_NAME;
    }

    /**
     * Returns the product Name of the given product ID
     * @param id ID value of the Product to query
     * @return The Product object with the matching ID value in the HashMap
     */
    public Product searchById(String id) {
        HashMap<String, Product> currentProductList = this.inventory.returnProductList();

        Product product = currentProductList.get(id);
        String name = product.getName();
        UI.printFormattedMessage(SRM_NAME_QUERY_FORMAT + NEWLINE, id, name);

        return product;
    }
}
