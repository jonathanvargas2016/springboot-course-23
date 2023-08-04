package com.jonathan.service.item.controller;

import com.jonathan.service.item.domain.Item;
import com.jonathan.service.item.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService; //dos servicios implementan itemService. Spring no sabe cual inyectar.

    @GetMapping("")
    public ResponseEntity<List<Item>> getAll() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/amount/{amount}")
    public ResponseEntity<Item> detail(@PathVariable Long id, @PathVariable Integer amount) {
        return new ResponseEntity<>(itemService.findById(id, amount), HttpStatus.OK);
    }


}
