package com.pharmacy.usermanagement.services;

import com.pharmacy.usermanagement.models.Admin;

import java.util.List;

public interface AdminService {


    public List<Admin> getAdmin();

    public Admin saveAdmin(Admin admin);

    public Admin updateAdmin(Admin admin, String id);

    public String deleteAdmin(String id);

    public Admin getAdminById(String id);
}
