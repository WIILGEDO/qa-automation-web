package steps.smoke;

import consts.SharedStateKeys;
import cucumber.api.java.en.And;
import dtos.catalog.Page;
import org.junit.Assert;
import pages.cp.*;
import utils.SessionStateHandler;

import static org.slf4j.LoggerFactory.getLogger;
import static pages.base.AbstractPage.onPage;

public class CartSteps {
    private String getProductPageName() {
        return SessionStateHandler.<Page>getValue(SharedStateKeys.PRODUCT_PAGE_DATA).getName();
    }

    @And("^add current product to cart")
    public void addToCart() {
        onPage(ProductPage.class).addToCart();
    }

    @And("^verify product '(.*)' is in cart")
    public void verifyProductInCart(String name){
        Assert.assertTrue("Product '" + name + "' was not found in cart", onPage(CartPage.class).isProductInList(name));
    }

    @And("^verify product is in cart")
    public void verifySessionProductInCart(){
        String name = getProductPageName();
        Assert.assertTrue("Product '" + name + "' was not found in cart", onPage(CartPage.class).isProductInList(name));
    }

    @And("^increase product quantity in cart for '(.*)'")
    public void increaseProductQuantityInCart(String name){
        onPage(CartPage.class).increaseQuantity(name);
    }

    @And("^increase session product quantity in cart")
    public void increaseSessionProductInCart(){
        String name = getProductPageName();
        onPage(CartPage.class).increaseQuantity(name);
    }

    @And("^decrease product quantity in cart for '(.*)'")
    public void decreaseProductQuantityInCart(String name){
        onPage(CartPage.class).decreaseQuantity(name);
    }

    @And("^decrease session product quantity in cart")
    public void decreaseSessionProductQuantityInCart(){
        String name = getProductPageName();
        onPage(CartPage.class).decreaseQuantity(name);
    }

    @And("^verify there are '(.*)' items for product '(.*)' in cart")
    public void verifyAmountInCartForProduct(Integer quantity,String name){
        Assert.assertEquals("Quantity check failed",quantity, onPage(CartPage.class).getProductQuantity(name));
    }

    @And("^verify there are '(.*)' items for product in cart")
    public void verifyAmountInCartForSessionProduct(Integer quantity){
        String name = getProductPageName();
        Assert.assertEquals("Quantity check failed",quantity, onPage(CartPage.class).getProductQuantity(name));
    }

    @And("^remove product from cart '(.*)'")
    public void cartRemoveProduct(String name){
        onPage(CartPage.class).removeProduct(name);
    }

    @And("^remove session product from cart")
    public void cartRemoveSessionProduct(){
        String name = getProductPageName();
        onPage(CartPage.class).removeProduct(name);
    }

    @And("^verify product is not in cart '(.*)'")
    public void productNotPresentInCart(String name){
        Assert.assertFalse("Product '" + name + "' is in cart", onPage(CartPage.class).isProductInList(name));
    }

    @And("^verify cart is empty")
    public void verifyCartIsEmpty(){
        Assert.assertTrue("Cart is not empty", onPage(CartPage.class).isEmpty());
    }


}

