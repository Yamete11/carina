package com.zebrunner.carina.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}?q=${city}&appid=${api_key}", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetWeatherMethod extends AbstractApiMethodV2 {

    public GetWeatherMethod(String city) {
        this(city, Configuration.getRequired("api_key"));
    }

    public GetWeatherMethod(String city, String apiKey) {
        replaceUrlPlaceholder("city", city);
        replaceUrlPlaceholder("api_key", apiKey);
        replaceUrlPlaceholder("base_url", Configuration.getRequired("base_url"));
    }
}
