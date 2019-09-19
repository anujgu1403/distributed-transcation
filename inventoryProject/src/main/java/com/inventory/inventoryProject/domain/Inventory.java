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
    private String productId;

    @Column(name="price")
    private double price;

    @Column(name="quantity")

    private int quantity;

}
