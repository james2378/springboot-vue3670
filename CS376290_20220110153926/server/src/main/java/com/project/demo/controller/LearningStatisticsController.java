package com.project.demo.controller;

import com.project.demo.entity.LearningStatistics;
import com.project.demo.service.LearningStatisticsService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *学习统计：(LearningStatistics)表控制层
 *
 */
@RestController
@RequestMapping("/learning_statistics")
public class LearningStatisticsController extends BaseController<LearningStatistics,LearningStatisticsService> {

    /**
     *学习统计对象
     */
    @Autowired
    public LearningStatisticsController(LearningStatisticsService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
