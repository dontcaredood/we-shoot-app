package com.weshoot.code.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;

@Entity
@Table(name = "ordersHistory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Transactional
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_sequence")
    @SequenceGenerator(name = "history_sequence", sequenceName = "history_sequence", initialValue = 1, allocationSize = 1)
    private long orderHistoryId;
    @Column(name = "orderId")
    private long orderId;
    @Column(name = "action")
    private String action;
    @Column(name = "data",length = 500)
    private String data;
    @Column(name = "timeStamp")
    private Date timeStamp;
    @Column(name = "userName")//change it to userid later
    private String userName;

//    @ManyToOne
//    @JoinColumn(name="order_id", nullable=false)
//    private Orders orders;

}
