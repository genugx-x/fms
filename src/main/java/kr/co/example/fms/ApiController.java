package kr.co.example.fms;

import kr.co.example.fms.model.ChannelData;
import kr.co.example.fms.model.MOF100Packet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.coyote.Response;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    final MongoTemplate mongoTemplate;

    // 데이터 호출
    @GetMapping("/mof100/data")
    public ResponseEntity<List<MOF100Packet>> findAll(HttpServletRequest request) {
        log.info("/mof100/data ---> {}", request.getRemoteHost());
        Query query = new Query();
        query.fields().include("createAt", "dataType", "deviceId", "hostAddress", "sourcePort", "payload");
        long start = System.currentTimeMillis();
        List<MOF100Packet> list =
                mongoTemplate.find(
                        query.with(Sort.by(Sort.Direction.DESC, "createAt")).limit(100)
                        , MOF100Packet.class);
        long end = System.currentTimeMillis();
        long time = end-start;
        long min = TimeUnit.MILLISECONDS.toMinutes(time);
        long sec = TimeUnit.MILLISECONDS.toSeconds(time);
        long remainMilliSec = time - TimeUnit.SECONDS.toMillis(sec);
        long remainSec = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(min);
        log.info("Data lookup time ----- {}.{}(sec)", sec, remainMilliSec);
        log.info("list.size():{}", list.size());
        return ResponseEntity.ok(list);
    }

    // 데이터 호출
    @GetMapping("/mof100/data/{deviceId}")
    public ResponseEntity<List<MOF100Packet>> findDataByDeviceId(HttpServletRequest request, @PathVariable Long deviceId) {
        log.info("/mof100/data/{} -->{}", deviceId, request.getRemoteHost());
        Query query = new Query();
        query.fields().include("createAt", "dataType", "deviceId", "hostAddress", "sourcePort", "payload");
        query.addCriteria(Criteria.where("deviceId").is(deviceId));
        long start = System.currentTimeMillis();
        List<MOF100Packet> list =
                mongoTemplate.find(
                        query.with(Sort.by(Sort.Direction.DESC, "createAt")).limit(100)
                        , MOF100Packet.class);

        // Collections.reverse(list);
        long end = System.currentTimeMillis();
        long time = end-start;
        long min = TimeUnit.MILLISECONDS.toMinutes(time);
        long sec = TimeUnit.MILLISECONDS.toSeconds(time);
        long remainMilliSec = time - TimeUnit.SECONDS.toMillis(sec);
        long remainSec = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(min);
        log.info("Data lookup time ----- {}.{}(sec)", sec, remainMilliSec);

        // log.info("list.size():{}", list.size());
        return ResponseEntity.ok(list);
    }

    // 데이터 호출
    @GetMapping("/test/data/{deviceId}/{createAt}")
    public ResponseEntity<List<MOF100Packet>> getDataTest(HttpServletRequest request, @PathVariable Long deviceId, @PathVariable String createAt) throws ParseException {
        //log.info("/test/data/{}/{} -->{}", deviceId, createAt, request.getRemoteHost());
        //log.info("{}", createAt);
        Query query = new Query();
        query.fields().include("createAt", "dataType", "deviceId", "hostAddress", "sourcePort", "payload");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = format.parse(createAt);
        query.addCriteria(Criteria.where("deviceId").is(deviceId).andOperator(Criteria.where("createAt").gt(date)));
        long start = System.currentTimeMillis();
        List<MOF100Packet> list =
                mongoTemplate.find(
                        query.with(Sort.by(Sort.Direction.ASC, "createAt")).limit(100)
                        , MOF100Packet.class);

        // Collections.reverse(list);
        long end = System.currentTimeMillis();
        long time = end-start;
        long min = TimeUnit.MILLISECONDS.toMinutes(time);
        long sec = TimeUnit.MILLISECONDS.toSeconds(time);
        long remainMilliSec = time - TimeUnit.SECONDS.toMillis(sec);
        long remainSec = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(min);
        // log.info("Data lookup time ----- {}.{}(sec)", sec, remainMilliSec);

        // log.info("list.size():{}", list.size());
        return ResponseEntity.ok(list);
    }

    // 데이터 호출
    @GetMapping("/test/data/{deviceId}")
    public ResponseEntity<List<MOF100Packet>> getDataTest(HttpServletRequest request, @PathVariable Long deviceId) {
        // log.info("/test/data/{} -->{}", deviceId, request.getRemoteHost());
        Query query = new Query();
        query.fields().include("createAt", "dataType", "deviceId", "hostAddress", "sourcePort", "payload");
        query.addCriteria(Criteria.where("deviceId").is(deviceId));
        long start = System.currentTimeMillis();
        List<MOF100Packet> list =
                mongoTemplate.find(
                        query.with(Sort.by(Sort.Direction.ASC, "createAt")).limit(100)
                        , MOF100Packet.class);
        long end = System.currentTimeMillis();
        long time = end-start;
        long min = TimeUnit.MILLISECONDS.toMinutes(time);
        long sec = TimeUnit.MILLISECONDS.toSeconds(time);
        long remainMilliSec = time - TimeUnit.SECONDS.toMillis(sec);
        long remainSec = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(min);
        // log.info("Data lookup time ----- {}.{}(sec)", sec, remainMilliSec);

        // log.info("list.size():{}", list.size());
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/download/csv")
    public void downloadCsv(HttpServletRequest request, HttpServletResponse response) {

        log.info("/download/csv-->{}", request.getRemoteHost());

        final String fileName = String.format("MOF100_%s.csv", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        Query query = new Query();
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MOF100Packet> list =
                mongoTemplate.find(
                        query.with(Sort.by(Sort.Direction.DESC, "createAt")).limit(86_400)
                        //query.with(Sort.by(Sort.Direction.DESC, "createAt")).limit(100)
                        , MOF100Packet.class);
        log.info("list.size():{}", list.size());
        try {
            response.setContentType("text/csv;charset=euc-kr");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + fileName + "\"");
            CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(),
                    CSVFormat.DEFAULT.withHeader(
                            "시각",
                            "data-type",
                            "device-id",
                            "ip",
                            "진동최대(ch1)", "진동최소(ch1)", "진동편차(ch1)", "진동평균(ch1)",
                            "전류최대(ch7)", "전류최소(ch7)", "전류편차(ch7)", "전류평균(ch7)"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            for (MOF100Packet packet : list){

                ChannelData ch1 = packet.getPayload().stream().filter(c->c.getNo()==1).findFirst().get();
                ChannelData ch7 = packet.getPayload().stream().filter(c->c.getNo()==7).findFirst().get();

                csvPrinter.printRecord(Arrays.asList(sdf.format(packet.getCreateAt()), packet.getDataType(), packet.getDeviceId(), packet.getHostAddress() + ":" + packet.getSourcePort(), ch1.getMax(), ch1.getMin(), ch1.getDeviation(), ch1.getAvg(), ch7.getMax(), ch7.getMin(), ch7.getDeviation(), ch7.getAvg()));

            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/download/csv/{deviceId}")
    public void downloadCsv(HttpServletRequest request, HttpServletResponse response, @PathVariable Long deviceId) {

        log.info("/download/csv/{}-->{}", deviceId, request.getRemoteHost());

        final String fileName = String.format("MOF100_deivceId_"+ deviceId +"_%s.csv", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        Query query = new Query();
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        query.addCriteria(Criteria.where("deviceId").is(deviceId));
        List<MOF100Packet> list =
                mongoTemplate.find(
                        query.with(Sort.by(Sort.Direction.DESC, "createAt")).limit(86_400)
                        // query.with(Sort.by(Sort.Direction.DESC, "createAt")).limit(100)
                        , MOF100Packet.class);
        log.info("list.size():{}", list.size());

        String userAgent = request.getHeader("User-Agent");
        try {
            log.info("fileName : {}", fileName);
            response.setContentType("text/csv;charset=euc-kr");
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + fileName + ";");
            } else {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"");
            }
            CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(),
                    CSVFormat.DEFAULT.withHeader(
                            "시각",
                            "data-type",
                            "device-id",
                            "ip",
                            "진동최대(ch1)", "진동최소(ch1)", "진동편차(ch1)", "진동평균(ch1)",
                            "전류최대(ch7)", "전류최소(ch7)", "전류편차(ch7)", "전류평균(ch7)"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            for (MOF100Packet packet : list){

                ChannelData ch1 = packet.getPayload().stream().filter(c->c.getNo()==1).findFirst().get();
                ChannelData ch7 = packet.getPayload().stream().filter(c->c.getNo()==7).findFirst().get();
                // log.info("csv : {}", Arrays.asList(sdf.format(packet.getCreateAt()), packet.getDataType(), packet.getDeviceId(), packet.getHostAddress() + ":" + packet.getSourcePort(), ch1.getMax(), ch1.getMin(), ch1.getDeviation(), ch1.getAvg(), ch7.getMax(), ch7.getMin(), ch7.getDeviation(), ch7.getAvg()));
                csvPrinter.printRecord(Arrays.asList(sdf.format(packet.getCreateAt()), packet.getDataType(), packet.getDeviceId(), packet.getHostAddress() + ":" + packet.getSourcePort(), ch1.getMax(), ch1.getMin(), ch1.getDeviation(), ch1.getAvg(), ch7.getMax(), ch7.getMin(), ch7.getDeviation(), ch7.getAvg()));

            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    // 사용안함
    /*
    @GetMapping(value = "/mof100/csv")
    public void mof100Dcsv(HttpServletRequest request, HttpServletResponse response) {

        log.info("/mof100/csv-->{}", request.getRemoteHost());

        final String fileName = String.format("MOF100_%s.csv", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        // deviceId 6번 아이디만 보이게
        Query query = new Query();

        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // if("device6".equals(userDetails.getUsername())) query.addCriteria(Criteria.where("deviceId").is(6));

        List<MOF100Packet> list =
                mongoTemplate.find(
                        //new Query().addCriteria(Criteria.where("deviceId").is(5))//.with(Sort.by(Sort.Direction.DESC, "createAt"))//.limit(86_400)
                       query.with(Sort.by(Sort.Direction.DESC, "createAt")).limit(100)
                        , MOF100Packet.class);

        //List<MOF100Packet> list = mongoTemplate.findAll (MOF100Packet.class, "packet");

        //log.info("list.size():{}", list.size());

        try {
            response.setContentType("text/csv;charset=euc-kr");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + fileName + "\"");
            CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(),
                    CSVFormat.DEFAULT.withHeader("시각", "data-type", "device-id", "ip", "ch1최대", "ch1최소", "ch1편차", "ch1평균", "ch7최대", "ch7최소", "ch7편차", "ch7평균"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            for (MOF100Packet packet : list){

                ChannelData ch1 = packet.getPayload().stream().filter(c->c.getNo()==1).findFirst().get();
                ChannelData ch7 = packet.getPayload().stream().filter(c->c.getNo()==7).findFirst().get();

                csvPrinter.printRecord(Arrays.asList(sdf.format(packet.getCreateAt()), packet.getDataType(), packet.getDeviceId(), packet.getHostAddress() + ":" + packet.getSourcePort(), ch1.getMax(), ch1.getMin(), ch1.getDeviation(), ch1.getAvg(), ch7.getMax(), ch7.getMin(), ch7.getDeviation(), ch7.getAvg()));

            }


        } catch (IOException e) {

            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }*/
}