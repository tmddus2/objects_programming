// # 클래스 작성자 - 클라이언트 프로그래머로 구분 (프로그래머의 역할
// # 클래스 작성자는 새로운 데이터 타입을 프로그램에 추가
// # 클라이언트 프로그래머는 클래스 작성자가 추가한 데이터 타입을 사용한다................. # 클래스를 엮어서 애플리케이션을 빠르고 안정적으로 구축
// # 클래스 자것ㅇ자는 필요한 부분만 공개하고 나머지는 꽁꽁 숨겨야한다.공개하고# 클라잉너트 프고그래머는 인텉ㅍ에시스만 알 수 있따. ==== 이를 구현ㅇ 은닉알고 ㅎ부른다.ㅎ부른다
// # 접근 제어 - 접근 수정자. 클래스 작성자는 puvlic 영역을 변경하지 않는다면 코드를 자유롭게 수정 ㄱ나ㅡㅇ.

public class Reservation{
    private Customer customer;
    private Screening screening;
    private Money fee;
    private int audienceCount;

    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount){
        this.customer = customer;
        this.screening = screening;
        this.fee =fee;
        this.audienceCount = audienceCount;
    }
}