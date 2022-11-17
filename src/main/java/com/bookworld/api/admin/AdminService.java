package com.bookworld.api.admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> getByEmail(String email);
}
