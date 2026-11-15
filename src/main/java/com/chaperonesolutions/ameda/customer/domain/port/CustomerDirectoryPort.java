package com.chaperonesolutions.ameda.customer.domain.port;

import com.chaperonesolutions.ameda.customer.domain.CustomerPost;
import com.chaperonesolutions.ameda.customer.domain.ExternalUser;

import java.util.List;

public interface CustomerDirectoryPort {

    ExternalUser findUser(int externalId);

    List<CustomerPost> findPosts(int externalId);

}
