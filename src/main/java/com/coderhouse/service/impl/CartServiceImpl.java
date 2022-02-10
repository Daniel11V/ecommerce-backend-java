package com.coderhouse.service.impl;

import com.coderhouse.builder.CartBuilder;
import com.coderhouse.model.document.Cart;
import com.coderhouse.model.document.CartProduct;
import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.request.CartRequest;
import com.coderhouse.model.request.NewCartRequest;
import com.coderhouse.model.response.CartResponse;
import com.coderhouse.repository.CartRepository;
import com.coderhouse.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public CartResponse createCart(NewCartRequest newCartRequest) {
        var newCart = CartBuilder.requestToDocument(newCartRequest);
        var document = cartRepository.save(newCart);

        return CartBuilder.documentToResponse(document);
    }

    @Override
    public CartResponse showCart(String cartCode) throws ApiRestException{
        var currentCart = getCart(cartCode);
        return CartBuilder.documentToResponse(currentCart);
    }

    @Override
    public CartResponse addToCart(String cartCode, CartRequest request) throws ApiRestException {

        Cart currentCart = getCart(cartCode);

        CartProduct repoItem = getItem(currentCart, request.getProductCode());
        if (!Objects.isNull(repoItem)) {
            throw new ApiRestException("Ya existe el producto "+repoItem.getProductCode()+" en el carrito");
        }

        currentCart.getItems().add(new CartProduct(request.getProductCode(), request.getQuantity()));

        var document = cartRepository.save(currentCart);
        return CartBuilder.documentToResponse(document);
    }

    @Override
    public CartResponse updateItem(String cartCode, CartRequest updatedItem) throws ApiRestException {
        var currentCart = getCart(cartCode);

        CartProduct repoItem = getItem(currentCart, updatedItem.getProductCode());
        if (Objects.isNull(repoItem)) {
            throw new ApiRestException("El producto "+updatedItem.getProductCode()+" no existe en el carrito");
        }

        currentCart.getItems().remove(repoItem);

        repoItem.setQuantity(updatedItem.getQuantity());
        currentCart.getItems().add(repoItem);

        var document = cartRepository.save(currentCart);
        return CartBuilder.documentToResponse(document);
    }

    @Override
    public CartResponse deleteItem(String cartCode, String productCode) throws ApiRestException {
        var currentCart = getCart(cartCode);

        CartProduct repoItem = getItem(currentCart, productCode);
        if (Objects.isNull(repoItem)) {
            throw new ApiRestException("El producto "+productCode+" no existe en el carrito");
        }

        currentCart.getItems().remove(repoItem);

        var document = cartRepository.save(currentCart);
        return CartBuilder.documentToResponse(document);
    }
//
//    @Override
//    public List<CartResponse> searchByCategory(String category) {
//        return CartBuilder.listDocumentToResponse(cartRepository.findByCategory(category));
//    }

    private Cart getCart(String cartCode) throws ApiRestException {
        if (cartCode.equals("0")) {
            throw new ApiRestException("El identificador del carrito debe ser mayor a 0");
        }

        var currentCart = cartRepository.findByCartCode(cartCode).orElse(null);

        if (Objects.isNull(currentCart)) {
            throw new ApiRestException("El carrito con codigo "+ cartCode +" no existe");
        }

        return currentCart;
    }

    private CartProduct getItem(Cart currentCart, String requestProductCode) throws ApiRestException {
        return currentCart.getItems().stream()
                .filter(product -> requestProductCode.equals(product.getProductCode()))
                .findAny()
                .orElse(null);
    }

}
