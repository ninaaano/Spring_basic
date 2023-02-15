package hello.hellospring.Controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
/**
 * 컨트롤러 어노테이션이 있으면 스프링 컨테이너가 멤버 컨트롤러 객체를 생성해서 넣어주고
 * 스프링이 관리를 해준다
 * 이걸 스프링 빈이 관리된다고 한다
 */
public class MemberController {
    private final MemberService memberService;

    /**
     * 스프링 컨테이너에 등록하면 하나만 등록이 된다
     * 부가적인 효과를 볼 수 있다
     * @param memberService
     */

    // @Autowired
    /**
     * 오토와이어를 사용하면 멤버 서비스를 가져다가 연결해준다
     * 근데 빨간 불이 떴다
     * 스프링 컨테이너에서 서비스를 가져온다.
     * 멤버 서비스는 순수한 자바 클래스기 때문이다
     * 멤버 서비스로 돌아가서 상단에 @서비스를 추가해주면 된다
     * 의존관계를 주입했다고 한다
     */
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // 조회할때 주로 사용 get
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 데이터를 등록할땐 post
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = "+member.getName());

        memberService.join(member);

        return "redirect:/"; // 홈화면으로 보내기
    }

    // 조회 기능
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // 멤버 다 가져오기
        model.addAttribute("members", members); // 모델에 담아서 뷰에 넘길거
        return "members/memberList";
    }
}
