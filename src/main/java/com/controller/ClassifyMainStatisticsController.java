package com.controller;

import com.entity.ClassifyMainStatistics;
import com.service.ClassifyMainStatisticsService;
import com.util.ChartDataJsonCreater;
import com.util.JSONConverter;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*
 * 分类统计相关接口
 */
@Controller
@RequestMapping("/statistics")
public class ClassifyMainStatisticsController {
    @Autowired
    private ClassifyMainStatisticsService classifyMainStatisticsService = null;

    public ClassifyMainStatisticsService getClassifyMainStatisticsService() {
        return classifyMainStatisticsService;
    }

    public void setClassifyMainStatisticsService(ClassifyMainStatisticsService classifyMainStatisticsService) {
        this.classifyMainStatisticsService = classifyMainStatisticsService;
    }

    /**
     * 首页轮播推荐书籍接口
     * http://localhost:8080/EMAN/statistics/query.htm?classifyMain=小说
     * @param classifyMain
     * @param out
     * @param request
     */
    @RequestMapping("/query.htm")
    @ResponseBody
    public void queryClassifyMainStatisticsByClassifyMain(@RequestParam(value="classifyMain")String classifyMain, PrintWriter out, HttpServletRequest request){
        ClassifyMainStatistics classifyMainStatistics = classifyMainStatisticsService.queryClassifyMainStatisticsByClassifyMain(classifyMain);
        String json;
        json = JSONConverter.convertToJSONString(classifyMainStatistics);
        out.print(json);
        out.flush();
    }

    /**
     * 首页轮播推荐书籍接口
     * http://localhost:8080/EMAN/statistics/getClassifyMainStatisticLineChartData.htm?classifyMain=小说
     * @param classifyMain
     * @param out
     * @param request
     */
    @RequestMapping("/getClassifyMainStatisticLineChartData.htm")
    @ResponseBody
    public void getClassifyMainStatisticLineChartData(@RequestParam(value="classifyMain")String classifyMain, PrintWriter out, HttpServletRequest request){
        ClassifyMainStatistics classifyMainStatistics = classifyMainStatisticsService.queryClassifyMainStatisticsByClassifyMain(classifyMain);
        ClassifyMainStatistics avgStatistics = classifyMainStatisticsService.queryClassifyMainStatisticsByClassifyMain("avg");
        System.out.println(classifyMainStatistics.getClassifyMain());

        double[][] data = {{0,0,0,0,0},{0,0,0,0,0}};
        data[0][0] = classifyMainStatistics.getReviewCount5();
        data[0][1] = classifyMainStatistics.getReviewCount4();
        data[0][2] = classifyMainStatistics.getReviewCount3();
        data[0][3] = classifyMainStatistics.getReviewCount2();
        data[0][4] = classifyMainStatistics.getReviewCount1();
        data[1][0] = avgStatistics.getReviewCount5();
        data[1][1] = avgStatistics.getReviewCount4();
        data[1][2] = avgStatistics.getReviewCount3();
        data[1][3] = avgStatistics.getReviewCount2();
        data[1][4] = avgStatistics.getReviewCount1();

        String[] dataLabels = {
                classifyMain,
                "全区平均"
        };
        String[] backgroundColor = {
                "rgb(255, 99, 132)",
                "rgb(255, 159, 64)"};
        String[] borderColor = {
                "rgb(255, 99, 132)",
                "rgb(255, 159, 64)"};
        String[] labels = {
                "5",
                "4",
                "3",
                "2",
                "1"
        };
        String json;
        try {
            json = ChartDataJsonCreater.getLineJson(data, dataLabels, backgroundColor, borderColor, labels, "各个评分的评分人数");
            System.out.println(json);
            out.print(json);
            out.flush();
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
