package lv.alija.bookShopMicro2.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lv.alija.bookShopMicro2.swagger.DescriptionVariables;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {DescriptionVariables.CLIENT})
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
}
