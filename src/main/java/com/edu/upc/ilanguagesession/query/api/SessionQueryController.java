package com.edu.upc.ilanguagesession.query.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
@Api(tags= "Sessions")
public class SessionQueryController {
}
