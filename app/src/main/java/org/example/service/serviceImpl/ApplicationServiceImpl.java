package org.example.service.serviceImpl;

import org.example.dto.AppRegisterDto;
import org.example.dto.AppRegisterResponse;
import org.example.entity.Application;
import org.example.repository.ApplicationRepository;
import org.example.repository.UserRepository;
import org.example.service.ApplicationService;
import org.example.utils.ApiKeyUtils;
import org.example.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ModelMapper modelMapper,
                                  UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public AppRegisterResponse registerApplication(AppRegisterDto registerDto) {
        Long currentUserId = SecurityUtils.getCurrentUserId();

        Application application = modelMapper.map(registerDto, Application.class);
        String apikey = ApiKeyUtils.generateApiKey();
        application.setApikey(apikey);
        application.setOwner(userRepository.getUserById(currentUserId));
        applicationRepository.save(application);
        return new AppRegisterResponse(application.getName(), apikey, application.getCategory());
    }
}
