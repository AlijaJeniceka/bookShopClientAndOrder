package lv.alija.bookShopMicro2.business.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lv.alija.bookShopMicro2.model.enums.ClientTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
public class ClientDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ClientTypes type;

    @Column(name = "age")
    private Long age;

    @Column(name = "discount")
    private Long discount;

    @Column(name = "document_for_discount")
    private boolean documentForDiscount;

}
