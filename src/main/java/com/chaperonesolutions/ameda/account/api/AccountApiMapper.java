package com.chaperonesolutions.ameda.account.api;

import com.chaperonesolutions.ameda.account.api.dto.AccountResponse;
import com.chaperonesolutions.ameda.account.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Domain to HTTP. Field names match one to one; {@code status} (enum) becomes its name.
 * A response field without a source fails the build instead of being silently null.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
interface AccountApiMapper {

    AccountResponse toResponse(Account account);

    List<AccountResponse> toResponses(List<Account> accounts);
}

