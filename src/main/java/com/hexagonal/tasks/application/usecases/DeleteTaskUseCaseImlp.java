package com.hexagonal.tasks.application.usecases;

import com.hexagonal.tasks.domain.ports.in.DeleteTaskUseCase;
import com.hexagonal.tasks.domain.ports.out.TaskRepositoryPort;

public class DeleteTaskUseCaseImlp implements DeleteTaskUseCase{

    private final TaskRepositoryPort repositoryPort;

    public DeleteTaskUseCaseImlp(TaskRepositoryPort repositoryPort){
        this.repositoryPort = repositoryPort;
    }

    @Override
    public boolean deleteTask(Long id) {
        return repositoryPort.deleteById(id);
    }

}
