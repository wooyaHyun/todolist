package com.example.todolist.service.logs;


import com.example.todolist.domain.logs.Logs;
import com.example.todolist.domain.logs.LogsRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogsService {
    private final LogsRepository logsRepository;

    @Transactional
    public void addLogs(String userId, HttpServletRequest request){
        logsRepository.save(Logs.builder()
                .userId(userId)
                .apiUri(request.getRequestURI())
                .requestIp(request.getRemoteAddr())
                .build()
        );
    }
}
