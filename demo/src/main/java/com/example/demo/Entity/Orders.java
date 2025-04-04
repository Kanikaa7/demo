package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @Column(name = "OrderID")
    int OrderID;

    @Column(name = "emailID")
    String emailID;

    @Column(name = "phoneNo")
    String phoneNo;

    @Column(name = "num_of_emails_per_phone")
    int num_of_emails_per_phone;

    @Column(name = "num_of_phones_per_email")
    int num_of_phones_per_email;

    @Column(name = "total_order_value")
    int total_order_value;

    @Column(name = "status")
    boolean status;
}
