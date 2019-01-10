package pl.project.investment.investment.response;


import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ResponseHeader {
    private String location;

    public HttpHeaders getHeader(String path, int id){
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(path)
                .buildAndExpand(id).toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return responseHeaders;
    }
}
