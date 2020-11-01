/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.service.impl;

import com.ec.model.Cart;
import com.ec.repository.ProductRepository;
import com.ec.response.ProductObject;
import com.ec.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.model.*;
import com.ec.repository.CartProductRepository;
import com.ec.repository.CartRepository;
import com.ec.repository.UserRepository;
import com.ec.request.AddToCartRequest;
import com.ec.request.AddUpdateProductObject;
import com.ec.response.AddUpdateProductResponse;
import com.ec.response.CommonResponse;

/**
 *
 * @author lsgp
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public List<ProductObject> getAllProducts() {
        List<ProductObject> productObjectList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            if (product.getProductAvailableQauntity() > 0) {
                ProductObject productObject = new ProductObject();
                productObject.setAvailQuantity(product.getProductAvailableQauntity());
                productObject.setDetails(product.getProductDetails());
                productObject.setId(product.getProductId());
                productObject.setName(product.getProductName());
                productObject.setPrice(product.getProductPrice());
                productObject.setRating(product.getProductRating());
                productObject.setSellerId(product.getSeller().getSellerId());
                productObject.setSellerName(product.getSeller().getSellerName());
                productObject.setUniqueId(product.getProductUniqueId());
                productObjectList.add(productObject);
            }
        }
        return productObjectList;
    }

    @Override
    public AddUpdateProductResponse addUpdateProduct(List<AddUpdateProductObject> productList, Seller seller) {
        AddUpdateProductResponse addUpdateProductResponse = new AddUpdateProductResponse();
        List<String> successIds = new ArrayList<>();
        List<String> failedIds = new ArrayList<>();
        for (AddUpdateProductObject addUpdateProductObject : productList) {
            Product product = productRepository.findByProductUniqueIdAndStatus(addUpdateProductObject.getUniqueId(), true);
            if (product == null) {  //If not exists the add new
                product = new Product();
                product.setProductUniqueId(addUpdateProductObject.getUniqueId());
            }
            product.setProductAvailableQauntity(addUpdateProductObject.getAvailQuantity());
            product.setProductDetails(addUpdateProductObject.getDetails());
            product.setProductName(addUpdateProductObject.getName());
            product.setProductPrice(addUpdateProductObject.getPrice());
            product.setProductRating(addUpdateProductObject.getRating());
            product.setSeller(seller);
            product.setStatus(true);
            product = productRepository.save(product);
            if (product != null) {
                successIds.add(addUpdateProductObject.getUniqueId());
            } else {
                failedIds.add(addUpdateProductObject.getUniqueId());
            }
        }
        addUpdateProductResponse.setFailedUniqueIds(failedIds);
        addUpdateProductResponse.setSuccessUniqueIds(successIds);
        return addUpdateProductResponse;
    }

    @Override
    public Product getProductByUniqueId(String uniqueId) {
        return productRepository.findByProductUniqueIdAndStatus(uniqueId, true);
    }

    @Override
    public CommonResponse addProductToCart(AddToCartRequest addToCartRequest) {
        CommonResponse commonResponse = new CommonResponse(2004, "Product added to cart successfully");
        Product product = productRepository.findByProductUniqueIdAndStatus(addToCartRequest.getUniqueId(), true);
        if (product != null) {
            if (product.getProductAvailableQauntity() >= addToCartRequest.getQuantity()) {
                //Fetch User
                User user = userRepository.findUserByUserId(); //check if cart is already available
                if (user != null) {
                    Cart cart = cartRepository.findCartByUserId(user.getUserId());
                    if (cart != null) {
                        //fetch all the products from cart
                        CartProduct cartProduct = cartProductRepository.findProductsByCartIdAdProductId(cart.getCartId(), product.getProductId());
                        if (cartProduct != null) {
                            //check exiting quantity in cart and newly added quantity
                            if (product.getProductAvailableQauntity() >= (cartProduct.getProductQuantity() + addToCartRequest.getQuantity())) {
                                //if it is less then update the cart price and product quantity
                                cartProduct.setProductQuantity(cartProduct.getProductQuantity() + addToCartRequest.getQuantity());
                                cartProductRepository.save(cartProduct);
                                cart.setCartTotalPrice(cart.getCartTotalPrice() + (addToCartRequest.getQuantity() * product.getProductPrice()));
                                cartRepository.save(cart);
                            } else {
                                commonResponse = new CommonResponse(3004, "Quantity can not exceeds than available");
                            }
                        } else {
                            //add new product in cart
                            cartProduct = new CartProduct();
                            cartProduct.setCart(cart);
                            cartProduct.setProduct(product);
                            cartProduct.setProductQuantity(addToCartRequest.getQuantity());
                            cartProduct.setStatus(true);
                            cartProductRepository.save(cartProduct);

                            cart.setCartTotalPrice(cart.getCartTotalPrice() + (cartProduct.getProductQuantity() * product.getProductPrice()));
                            cartRepository.save(cart);
                        }
                    } else {
                        //Create new cart for that user
                        cart = new Cart();
                        cart.setUser(user);
                        cart.setStatus(true);
                        cartRepository.save(cart);

                        //create new product for that user
                        CartProduct cartProduct = new CartProduct();
                        cartProduct.setCart(cart);
                        cartProduct.setProduct(product);
                        cartProduct.setProductQuantity(addToCartRequest.getQuantity());
                        cartProduct.setStatus(true);
                        cartProductRepository.save(cartProduct);

                        //update the final cart price
                        cart.setCartTotalPrice(cartProduct.getProductQuantity() * product.getProductPrice());
                        cartRepository.save(cart);
                    }
                } else {
                    commonResponse = new CommonResponse(3005, "User not found");
                }
            } else {
                commonResponse = new CommonResponse(3004, "Quantity can not exceeds than available");
            }
        } else {
            commonResponse = new CommonResponse(3003, "Product not found");
        }
        return commonResponse;
    }

}
