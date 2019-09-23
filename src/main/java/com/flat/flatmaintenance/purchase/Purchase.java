package com.flat.flatmaintenance.purchase;

import com.flat.flatmaintenance.product.Product;
import com.flat.flatmaintenance.user.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    private List<Product> product;

    @CreationTimestamp
    private Date createdAt;

    private Integer quantity;

    private String comment;

}
