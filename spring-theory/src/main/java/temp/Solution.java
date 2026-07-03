package temp;


import java.util.Arrays;

class Solution {

   /*
   long startTime = System.currentTimeMillis();

// 실행할 코드 구간
myService.doSomething();

long endTime = System.currentTimeMillis();
System.out.println("실행 시간: " + (endTime - startTime) + "ms");
    */
    // 중위험군이라는 문자열을 준다. ex)"asfda"
    // 고위험군이라는 문자열은 aieou 모음이면 중위험군에서 걸러지면 고위험군은 아니다.
    // 저위험군은 저 둘에 해당하지 않는 문자이다.
    // 문자는 모두 소문자이며 매개변수는 중위험군 문자열과 각 문자열을 검사하기 위한 문자열 배열이 주어진다.
    // 검사조건은 저위험군과 고위험군이 서로 짝지어서 정렬이 되면 된다. ab, eddeee이렇게 주어졌다면
    //->edeede이렇게 짝지을수 있으니 가능 가능하면 yes, 안되면 no반환

    //직원 1명 감당할 수 있는 수 m, 직원에 수행할 수 있는 시간 k, 각시간당(총 1000개까지) 손님수 배열이 주어진다.
    //배열은 1시간당씩 쪼개지며, m미만의 손님수는 직원이 필요없다. 배열에는 손님수가 있고 해당 손님수를 커버할 직원의 수가 필요하다.
    //총 필요한 직원의 수를 반환하면된다.
    //직원의 수를 시간으로 할 필요가 없다.
    //시간 빼기는 직원의 수만큼 하기
    //직원 시간 관리용 배열 만들기 사이즈는 똑같이 손님수배열 사이즈만큼
    //현재 시간[i]에서 직원을 고용한다면 직원이 퇴근하는 시간인 시간 관리용 배열[i+k]에 해당직원수를 넣기
    //시간관리용 배열에 도착했을 때 값이 0이 아니라면 현재 직원의 수를 해당 수만큼 뺀다.
    //이러면 해결이 된다는 거였음
    static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        String moderate = "aefh";
        String[] check = {"eeeefffwww","asdferffd", "ejfiienks", "fefadf"};
        System.out.println(Arrays.toString(solution(moderate, check)));
        long endTime = System.currentTimeMillis();
        System.out.println("실행 시간: " + (endTime - startTime) + "ms");
    }
    public static String[] solution(String moderate, String[] check) {
        int highrow= 0, lowrow = 0;
        int temp = 0;
        String[] answer = new String[check.length];
        for(String i: check) {
            highrow = 0;
            lowrow = 0;
            for(Character c: i.toCharArray()) {
                if(moderate.indexOf(c) != -1) continue;
                if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') highrow++;
                else lowrow++;
            }
            if((highrow * 2 >= lowrow && lowrow >= highrow) || (lowrow * 2 >= highrow && highrow >= lowrow)) answer[temp] = "yes";
            else answer[temp] = "no";
            temp++;
        }
        return answer;
    } // 해결?

    /*
    행이 지역 0번이 그거
행마다 순회해서 확인하면 됨

손님의 수를 시간(m)으로 나눈 몫이 결국 필요한 직원의 수 1000

직원의 수만큼 내려야함
시간에 대한 카운트? 5씩늘어나잖아 5씩늘어나고 이거를
+5-1 /
직원과 고용시간에 대한 배열 1000, 1000
현재 남아있는 직원 수

현재 직원의 수 는 시간수에서 나누기해서 구하기
현재 남아있는 시간 수(m*k) curTime
for문으로 손님의 수를 계속 확인한다. 손님수/m의 몫으로 필요한 직원의 수 확인
남아있는 시간수가 있을때만 빼야함
시간 -1;
직원의 수는 (curTime + k -1) /k;
필요한 직원의 수 만큼 m*k를 더한다.
count +필요한 직원의수

x가 행이
y가 열이고

1. 잘라낸 값을 리턴 함수 (무조건 범위안)
	붙일 경로를 체크 x2-x1+1, y2-y1+1
2. 붙일 함수
	해당 좌표에 붙일 경로를 붙였을때 넘는다면 최댓값까지
	아니라면 경로만큼 for문 수행후 리턴

직원수를 구하고 시간을 구하기?
1 7

neutral가 아니면서
자음일때 맞고 아니고
모음일때 맞고 맞고 그러면 x
모음일때 아니고 맞고 그러면 o
aeiou인거 카운트
나머지는 다른거 카운트 비교후 결과 반환 *2면 됨

개수 구한다음에 서로 연결만 되면 되겠는데?
     */
}