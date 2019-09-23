package com.inventory.inventoryProject.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="productId")
    private String productId;

    @Column(name="quantity")
    private int quantity;

}
