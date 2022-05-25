package com.openhelp.profile.service;

import com.openhelp.profile.model.User;

/**
 * @author Pavel Ravvich.
 */
public interface MailService {
    void sendVerification(User user);
}
