package com.chaperonesolutions.ameda.customer.api;


import com.chaperonesolutions.ameda.customer.api.dto.CreateCustomerRequest;
import com.chaperonesolutions.ameda.customer.api.dto.CustomerPostResponse;
import com.chaperonesolutions.ameda.customer.api.dto.CustomerResponse;
import com.chaperonesolutions.ameda.customer.api.dto.PageResponse;
import com.chaperonesolutions.ameda.customer.domain.Customer;
import com.chaperonesolutions.ameda.customer.domain.CustomerDetails;
import com.chaperonesolutions.ameda.customer.domain.CustomerPost;
import com.chaperonesolutions.ameda.shared.mapping.MapStructConfig;
import com.chaperonesolutions.ameda.shared.pagging.PageResult;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface CustomerApiMapper {

    CustomerDetails toDomain(CreateCustomerRequest request);

    @Mapping(target = "externalUserId", source = "externalId")
    @Mapping(target = "fullName", source = "details.fullName")
    @Mapping(target = "email", source = "details.email")
    @Mapping(target = "phone", source = "details.phone")
    @Mapping(target = "city", source = "details.city")
    @BeanMapping(ignoreUnmappedSourceProperties = {"linkedToDirectory","version","updatedAt"})
    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponses(List<Customer> customers);

    CustomerPostResponse toPostResponse(CustomerPost post);

    List<CustomerPostResponse> toPostResponses(List<CustomerPost> posts);

    /** Generic wrapper: written by hand, the element mapping is generated. */
    default PageResponse<CustomerResponse> toPageResponse(PageResult<Customer> page) {
        return new PageResponse<>(toResponses(page.content()), page.page(), page.size(),
                page.totalElements(), page.totalPages());
    }

}