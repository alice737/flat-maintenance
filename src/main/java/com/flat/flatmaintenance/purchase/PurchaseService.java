package com.flat.flatmaintenance.purchase;

import com.flat.flatmaintenance.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public Optional<Purchase> findOne(Long id ){
        return purchaseRepository.findById(id);
    }

    public void deleteById(Long id){
        purchaseRepository.deleteById(id);
    }

     public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
     }

}
