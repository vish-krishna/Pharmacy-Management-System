package com.pharmacy.usermanagement.services;

import com.pharmacy.usermanagement.models.Doctor;

import java.util.List;

public interface DoctorService {


    public List<Doctor> getDoctor();

    public Doctor saveDoctor(Doctor doctor);

    public Doctor updateDoctor(Doctor doctor, String id);

    public String deleteDoctor(String id);
}
