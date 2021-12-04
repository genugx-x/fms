package kr.co.example.fms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ViewController {

    @GetMapping
    public ResponseEntity<?> healthCheck(HttpServletRequest request) {
        log.info("/ --> {}", request.getRemoteHost());
        return ResponseEntity.ok("I'm very healthy. Thank you!");
    }

    @GetMapping("/mof100_packets")
    public String home(HttpServletRequest request) {
        log.info("/mof100_packets --> {}", request.getRemoteHost());
        return "mof100_packets";
    }

    @GetMapping("/chart/{deviceId}")
    public String graph(HttpServletRequest request, @PathVariable Integer deviceId) {
        String view = null;
        switch (deviceId) {
            case 1: view = "eco_aqua"; break;
            case 2: view = "join01"; break;
            case 3: view = "join02"; break;
            case 4: view = "central01"; break;
            case 5: view = "central02"; break;
            case 6: view = "sgfs"; break;
        }
        log.info("/chart/{} <---- {}", view, request.getRemoteHost());
        return "chart/" + view;
    }
}
