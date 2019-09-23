package com.flat.flatmaintenance.purchase;

import com.flat.flatmaintenance.exceptions.PurchaseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController("api/purchase")
@Slf4j
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> findById(@PathVariable Long id) {
        Optional<Purchase> purchase = purchaseService.findOne(id);
        if (!purchase.isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.badRequest().build();
        }
        log.info("Purchase by id " + id + " is existed");

        return ResponseEntity.ok(purchase.get());
    }

    @GetMapping()
    public ResponseEntity<List<Purchase>> findAll() {
        return ResponseEntity.ok(purchaseService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Purchase purchase) {
        return ResponseEntity.ok(purchaseService.save(purchase));
    }

    @DeleteMapping()
    public ResponseEntity delete(@PathVariable Long id) {
        if (!purchaseService.findOne(id).isPresent()) {
            log.error("Id " + id + " is not existed");
            return ResponseEntity.badRequest().build();
        }
        purchaseService.deleteById(id);
        log.info("Id " + id + " is existed");

        return ResponseEntity.ok().build();
    }

    @GetMapping("/products")
    void findProductsByUserId(){

    }

    @GetMapping("/purchases")
    void findPurchaseByUserId(){

    }

    @GetMapping("productbypurchase")
    void findProductsByPurchaseId(){

    }
}
