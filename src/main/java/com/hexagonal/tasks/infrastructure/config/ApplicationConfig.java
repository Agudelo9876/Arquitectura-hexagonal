package com.hexagonal.tasks.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hexagonal.tasks.application.services.TaskService;
import com.hexagonal.tasks.application.usecases.CreateTaskUseCaseImlp;
import com.hexagonal.tasks.application.usecases.DeleteTaskUseCaseImlp;
import com.hexagonal.tasks.application.usecases.GetAdditionalTaskInfoUseCaseImlp;
import com.hexagonal.tasks.application.usecases.RetrieveTaskUseCaseImlp;
import com.hexagonal.tasks.application.usecases.UpdateTaskUseCaseImlp;
import com.hexagonal.tasks.domain.ports.in.GetAdditionalTaskInfoUseCase;
import com.hexagonal.tasks.domain.ports.out.ExternalServicePort;
import com.hexagonal.tasks.domain.ports.out.TaskRepositoryPort;
import com.hexagonal.tasks.infrastructure.adapters.ExternalServiceAdapter;
import com.hexagonal.tasks.infrastructure.repositories.JpaTaskRepositoryAdapter;

@Configuration
public class ApplicationConfig {
    
    @Bean
    public TaskService taskService(TaskRepositoryPort taskRepositoryPort, GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase){
        return new TaskService(
            new CreateTaskUseCaseImlp(taskRepositoryPort),
            new DeleteTaskUseCaseImlp(taskRepositoryPort),
            new RetrieveTaskUseCaseImlp(taskRepositoryPort),
            new UpdateTaskUseCaseImlp(taskRepositoryPort),
            getAdditionalTaskInfoUseCase
        );
    }

    @Bean 
    public TaskRepositoryPort taskRepositoryPort(JpaTaskRepositoryAdapter jpaTaskRepositoryAdapter){
        return jpaTaskRepositoryAdapter;
    }

    @Bean 
    public GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase(ExternalServicePort externalServicePort){
        return new GetAdditionalTaskInfoUseCaseImlp(externalServicePort);
    }

    @Bean
    public ExternalServicePort externalServicePort(){
        return new ExternalServiceAdapter();
    }
}
