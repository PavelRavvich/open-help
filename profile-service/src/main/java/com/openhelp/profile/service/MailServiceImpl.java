package com.openhelp.profile.service;

import com.openhelp.profile.model.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * @author Pavel Ravvich.
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Override
    public void sendVerification(@NotNull User user) {
        log.debug("Send verification code for {}", user.getUsername());
    }
}
