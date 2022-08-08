package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {
    int increaseNo = -1;
    //실습1
    @RequestMapping("/sbb")
    @ResponseBody //아래 함수의 리턴값을 문자열화해 그대로 브라우저의 응답에 담는다.
    public String index() {
        return "Hello"; // 브라우저에 담긴다
    }
    //실습2
    @GetMapping ("/page1")
    @ResponseBody
    public String showPage() {
        return """
                <form method="POST" action="/page2">
                    <input type="text" name="age" placeholder="나이" />
                    <input type="submit" value="page2 POST방식으로 이동" />
                    </form>
                """;
    }
    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요. POST방식으로 오셨군요.</h1>
                """.formatted(age);
    }
    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요. Get방식으로 오셨군요.</h1>
                """.formatted(age);
    }
    //실습3
    @GetMapping("/plus")
    @ResponseBody
    public int showPlus(int a, int b) {
        return a + b;
    }
    @GetMapping("/minus")
    @ResponseBody
    public int showMinus(int a, int b) {
        return a - b;
    }
    @GetMapping("/increase")
    @ResponseBody
    public int showIncrease() {
        increaseNo++;
        return increaseNo;
    }
    //실습4
    @GetMapping("/gugudan")
    @ResponseBody
    public String gugudan(int dan, int limit) {
        return IntStream.rangeClosed(1, limit)
                .mapToObj(i -> "%d * %d = %d".formatted(dan, i, dan*i))
                .collect(Collectors.joining("<br>\n"));
    }
    //실습5
    @GetMapping("/mbti")
    @ResponseBody
    public String mbti(String name) {
        if(name.equals("홍길동")) return "INFP";
        if(name.equals("홍길순")) return "ENFP";
        if(name.equals("임꺽정")) return "INPJ";
        if(name.equals("본인")) return "ESTJ";
        return "unknown";
    }
    @GetMapping("/mbti/{name}")
    @ResponseBody
    public String mbti2(@PathVariable String name) {
        String rs = switch(name) {
            case "홍길동" -> "INFP";
            case "홍길순" -> "ENFP";
            case "임꺽정", "임걱정" -> "INPJ";
            case "본인" -> "ESTJ";
            default -> "unknown";
        };
        return rs;
//        return switch(name) {
//            case "홍길동" -> "INFP";
//            case "홍길순" -> "ENFP";
//            case "임꺽정" -> "INPJ";
//            case "본인" -> "ESTJ";
//            default -> "unknown";
//        };
    }
//    @GetMapping("/saveSessionAge")
//    @ResponseBody
//    public void
}
