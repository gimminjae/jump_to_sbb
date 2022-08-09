package com.ll.exam.sbb;

import com.sun.tools.javac.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {
    private List<Article> list = new LinkedList<>();
    private List<Person> personList = new LinkedList<>();

    private long articleNum = 0;
    private int increaseNo = -1;
    public MainController() {
        makeTestData();
    }
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
    //실습6
    @GetMapping("/saveSession/{name}/{value}")
    @ResponseBody
    public String saveSession() {
        return "";
    }
    @GetMapping("/getSession/{name}")
    @ResponseBody
    public String getSessionAge() {
        return "";
    }
    //실습7
    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(String title, String body) {
        Article article = new Article(title, body);
        list.add(article);
        return "%d번 글이 등록되었습니다.".formatted(article.getId());
    }
    @GetMapping("/article/{id}")
    @ResponseBody
    public Object getArticle(@PathVariable int id) {
        Article article = findById(id);
        if(article == null) return "%d번 글은 존재하지 않습니다.".formatted(id);
        return article;
    }
    //실습8
    @GetMapping("/modifyArticle")
    @ResponseBody
    public String modifyArticle(long id, String title, String body) {
        Article article = findById(id);
        if(article == null) return "%d번 글은 존재하지 않습니다.".formatted(id);
        article.setTitle(title);
        article.setBody(body);
        return "%d번 글이 수정되었습니다.".formatted(article.getId());
    }
    @GetMapping("/deleteArticle")
    @ResponseBody
    public String deleteArticle(long id) {
        Article article = findById(id);
        if(article == null) return "%d번 글은 존재하지 않습니다.".formatted(id);
        list.remove(article);
        return "%d번 글이 삭제되었습니다.".formatted(id);
    }
    //실습9
    @GetMapping("/addPerson")
    @ResponseBody
    String addPerson1(Person p) {
        personList.add(p);
        return "%d번 %s (이)가 생성되었습니다.".formatted(p.getId(), p.getName());
    }
    @GetMapping("/addPerson/{id}")
    @ResponseBody
    String addPerson2(Person p) {
        personList.add(p);
        return "%d번 %s (이)가 생성되었습니다.".formatted(p.getId(), p.getName());
    }




    //Util
    public void makeTestData() {
        list.add(new Article("title1", "body1"));
        list.add(new Article("title2", "body2"));
        list.add(new Article("title3", "body3"));
    }


    public Article findById(long id) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == id) return list.get(i);
        }
        return null;
    }
}
