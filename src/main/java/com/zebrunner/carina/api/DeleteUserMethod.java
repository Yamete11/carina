package com.zebrunner.carina.api;

import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "http://localhost:8080/api/users/${id}", methodType = HttpMethodType.DELETE)
@SuccessfulHttpStatus(status = HttpResponseStatusType.NO_CONTENT_204)
public class DeleteUserMethod extends AbstractApiMethodV2 {

    public DeleteUserMethod(Long id) {
        replaceUrlPlaceholder("id", String.valueOf(id));
    }
}
