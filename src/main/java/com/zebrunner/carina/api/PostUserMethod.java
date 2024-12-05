package com.zebrunner.carina.api;

import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "http://localhost:8080/api/users", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/users/_post/rq.json")
@ResponseTemplatePath(path = "api/users/_post/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostUserMethod extends AbstractApiMethodV2 {

    public PostUserMethod() {
        super("api/users/_post/rq.json", "api/users/_post/rs.json");
    }
}

