package hello.hellospring.repository;// @ author ninaaano

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
//@Repository // 데이터 저장
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //  0,1,2 key값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환 될 확률이 있으면 사용. null이여도 감싸서 반환. 클라이언트에서 뭘 할수가 있다
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나로도 찾음
    }

    // 메모리 비워주기
    public void clearStore() {
        store.clear();
    }
}
