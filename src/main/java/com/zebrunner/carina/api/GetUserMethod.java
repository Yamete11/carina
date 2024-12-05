package com.zebrunner.carina.api;

import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "http://localhost:8080/api/${request}", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetUserMethod extends AbstractApiMethodV2 {

    public GetUserMethod() {
        replaceUrlPlaceholder("request", "users");
    }

    public GetUserMethod(String end) {
        replaceUrlPlaceholder("request", end);
    }

    public GetUserMethod(Long id) {
        replaceUrlPlaceholder("request", "users/getUser/" + id);
    }
}
