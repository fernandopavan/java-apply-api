package com.projeto.apply.resources;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/source")
public class SourceResource {

    @ApiOperation(value = "Retorna o link do projeto no Github")
    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok().body("https://github.com/fernandopavan/java-apply-api \n " +
                " https://github.com/fernandopavan/java-apply-front");
    }

}
