package com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder;


import com.chaperonesolutions.ameda.customer.domain.CustomerDetails;
import com.chaperonesolutions.ameda.customer.domain.CustomerPost;
import com.chaperonesolutions.ameda.customer.domain.ExternalUser;
import com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder.dto.JsonPlaceholderPost;
import com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder.dto.JsonPlaceholderUser;
import com.chaperonesolutions.ameda.shared.mapping.MapStructConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface JsonPlaceholderMapper {

    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "details", source = "user")
    ExternalUser toDomain(JsonPlaceholderUser user);

    @Mapping(target = "fullName", source = "name")
    @Mapping(target = "city", source = "address.city")
    CustomerDetails toDetails(JsonPlaceholderUser user);

    CustomerPost toDomain(JsonPlaceholderPost post);

    List<CustomerPost> toDomain(List<JsonPlaceholderPost> posts);
}
