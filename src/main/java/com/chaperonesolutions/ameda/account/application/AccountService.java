package com.chaperonesolutions.ameda.account.application;

import com.chaperonesolutions.ameda.account.domain.Account;
import com.chaperonesolutions.ameda.account.domain.exception.AccountNotFoundException;
import com.chaperonesolutions.ameda.account.domain.port.AccountRepositoryPort;
import com.chaperonesolutions.ameda.account.domain.port.CustomerLookupPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepositoryPort accounts;
    private final CustomerLookupPort customers;

    public Account open(UUID customerId, String currency) {
        customers.requireExists(customerId);
        Account account = accounts.save(Account.open(customerId, currency, Instant.now()));
        log.info("Account opened: account={} customer={} currency={}",
                account.id(), customerId, account.currency());
        return account;
    }

    @Transactional(readOnly = true)
    public Account get(UUID accountId) {
        return load(accountId);
    }

    @Transactional(readOnly = true)
    public List<Account> accountsOf(UUID customerId) {
        customers.requireExists(customerId);
        return accounts.findByCustomerId(customerId);
    }

    public Account deposit(UUID accountId, BigDecimal amount) {
        Account account = accounts.save(load(accountId).deposit(amount));
        log.info("Deposit: account={} amount={} {}", accountId, amount, account.currency());
        return account;
    }

    public Account withdraw(UUID accountId, BigDecimal amount) {
        Account account = accounts.save(load(accountId).withdraw(amount));
        log.info("Withdrawal: account={} amount={} {}", accountId, amount, account.currency());
        return account;
    }

    public Account close(UUID accountId) {
        Account account = accounts.save(load(accountId).close());
        log.info("Account closed: account={}", accountId);
        return account;
    }

    private Account load(UUID accountId) {
        return accounts.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
