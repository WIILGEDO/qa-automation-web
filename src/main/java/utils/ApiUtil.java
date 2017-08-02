package utils;

import consts.SharedStateKeys;
import dtos.catalog.Page;
import dtos.catalog.ProductByNinResponse;
import org.slf4j.Logger;
import services.ProductService;

import static org.slf4j.LoggerFactory.getLogger;


public class ApiUtil {

    private static final Logger LOG = getLogger(ApiUtil.class);

    // adds an available product, retrieved using api, to the session state handler
    public static Page setAvailableProduct(){
        ProductService productService = new ProductService();
        Page page = productService.getProduct("Cake");

        if(page!=null){
            SessionStateHandler.setValue(SharedStateKeys.PRODUCT_PAGE_DATA, page);
            LOG.info("Available product added to session data { " + page.getName() + " | " + page.getNin() + " }" );
        }else {
            LOG.info("Could not find an available product !!!");
        }
        return page;
    }

    // adds a test product, retrieved using api, to the session state handler
    public static ProductByNinResponse setAvailableTestProduct(){
        ProductService productService = new ProductService();
        ProductByNinResponse productByNinResponse = productService.getTestProduct();

        if(productByNinResponse!=null){
            SessionStateHandler.setValue(SharedStateKeys.PRODUCT_BY_NIN_DATA,productByNinResponse);
            LOG.info("Available product added to session data { " + productByNinResponse.getName() + " | " + productByNinResponse.getNin() + " }" );
        }else {
            LOG.info("Could not find an available product !!!");
        }
        return productByNinResponse;
    }

}
