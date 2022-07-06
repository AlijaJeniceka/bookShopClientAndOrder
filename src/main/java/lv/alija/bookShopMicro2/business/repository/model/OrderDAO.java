package lv.alija.bookShopMicro2.business.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lv.alija.bookShopMicro2.model.Book;
import lv.alija.bookShopMicro2.model.Client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order")
public class OrderDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "books")
    private List<Book> booksList;

    @Column(name = "client_id")
    private Client clientId;

    @Column(name = "total_price")
    private Long totalPrice;
}
