package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private List<PurchaseLine> line;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private boolean paid;

    @Column(nullable = false)
    private boolean delivered;

    @Column(nullable = false)
    private boolean archived;

    @Column(name = "user_id")
    private Long userId; // Le type est Long, comme prévu

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PurchaseLine> getLine() {
        return line;
    }

    public void setLine(List<PurchaseLine> line) {
        this.line = line;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Constructeur par défaut
    public Purchase() {
    }

    // Constructeur avec paramètres
    public Purchase(List<PurchaseLine> line, Date date, boolean paid, boolean delivered, boolean archived, Long userId) {
        this.line = line;
        this.date = date;
        this.paid = paid;
        this.delivered = delivered;
        this.archived = archived;
        this.userId = userId;
    }
}
