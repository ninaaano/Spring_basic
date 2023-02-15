package hello.hellospring.Controller;// @ author ninaaano

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // RequestParam 위에서 커맨드 + p
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam(value = "name") String name,Model model) {
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 바디에 데이터를 직접 넣어주겠따
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    } // 실행시키면 html 바디가 다 사라진다. 내가 적은 글자만 나옴

    // api 방식. json 방식(검색해보기) 요새는 다 json 방식으로 통일되었다
    @GetMapping("hello-api")
    @ResponseBody // json으로 반환하는게 기본이다.
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // 커맨드 + 쉬프트 + 엔터 = 자동완성
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
