package com.coderhouse.controller;

import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.request.CartRequest;
import com.coderhouse.model.request.NewCartRequest;
import com.coderhouse.model.response.CartResponse;
import com.coderhouse.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ecommerce/cart")
public class CartController {

    private final CartService service;

    @GetMapping("/{cartCode}")
    public CartResponse showCart(@PathVariable String cartCode) throws ApiRestException{
        return service.showCart(cartCode);
    }

    @PostMapping("/create")
    public CartResponse createCart(@Valid @RequestBody NewCartRequest newCart) {
        return service.createCart(newCart);
    }

    @PostMapping("/{cartCode}/add")
    public CartResponse create(@PathVariable String cartCode,
           @Valid @RequestBody CartRequest newItem) throws ApiRestException {
        return service.addToCart(cartCode, newItem);
    }

    @PutMapping("/{cartCode}")
    public CartResponse update(@PathVariable String cartCode,
                                         @Valid @RequestBody CartRequest updatedItem) throws ApiRestException {
        return service.updateItem(cartCode, updatedItem);
    }


    @DeleteMapping("/{cartCode}/{productCode}")
    public CartResponse delete(@PathVariable String cartCode,@PathVariable String productCode) throws ApiRestException {
        return service.deleteItem(cartCode, productCode);
    }

}
