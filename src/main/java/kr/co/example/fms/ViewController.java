package kr.co.qsol.fishery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ViewController {

    @GetMapping
    public ResponseEntity<?> healthCheck(HttpServletRequest request, Model model) {
        log.info("/ --> {}", request.getRemoteHost());
        return ResponseEntity.ok("I'm very healthy. Thank you!");
    }

    @GetMapping("/home")
    public String ecoaqua(HttpServletRequest request, Model model) {
        log.info("/home --> {}", request.getRemoteHost());
        return "home";
    }

    @GetMapping("/chart")
    public String graph(HttpServletRequest request, Model model) {
        log.info("/chart", request.getRemoteHost());
        return "chart";
    }
}
