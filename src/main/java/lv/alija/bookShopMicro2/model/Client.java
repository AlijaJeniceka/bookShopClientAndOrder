package lv.alija.bookShopMicro2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.alija.bookShopMicro2.model.enums.ClientTypes;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ApiModel(description = "Model of client data")
public class Client {

    @ApiModelProperty(notes = "The unique id for the client")
    @Min(value = 1)
    private Long id;

    @ApiModelProperty(notes = "Client's type")
    @NotNull
    private ClientTypes type;

    @ApiModelProperty(notes = "Client's age")
    @NotNull
    @Min(value=0)
    private Long age;

    @ApiModelProperty(notes = "Client's discount")
    private Long discount;

    @ApiModelProperty(notes = "Client have or not document to receive discount")
    private boolean documentForDiscount;

}
