package lv.alija.bookShopMicro2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Model of order")
@NoArgsConstructor
@Data
public class Order implements Serializable {

    @ApiModelProperty(notes = "The unique id of the order should be more then 0")
    @Min(value = 1)
    private Long id;

    @ApiModelProperty(notes = "Liat of the chosen books to buy")
    private List<Book> booksList;

    @ApiModelProperty(notes = "The client id for the discount counting")
    private Client clientId;

    @ApiModelProperty(notes = "Total price of the books prices with discount")
    @Min(value = 0)
    private Long totalPrice;

}
