package org.example.service;

import org.example.dto.AppRegisterDto;
import org.example.dto.AppRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationService {
    AppRegisterResponse registerApplication(AppRegisterDto registerDto);
}
