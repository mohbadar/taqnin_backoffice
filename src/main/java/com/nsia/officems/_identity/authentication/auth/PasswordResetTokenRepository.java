package com.nsia.officems._identity.authentication.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
   public PasswordResetToken findByToken(String token);
}