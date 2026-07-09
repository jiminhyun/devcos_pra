package temp;


import java.util.Arrays;

public class MyArrayList {
    //연결리스트 부분은 직접 작성했습니다.

    // [Step 1] 필드 (작성돼 있음): 데이터를 담을 배열과 현재 개수
    private String[] arr = new String[10];
    private int size = 0;

    // [Step 2] 맨 뒤에 추가
    void addLast(String data) {
        // TODO: 빈 끝자리(size 위치)에 data를 넣고, size를 1 늘리세요.
        arr[size++] = data;
    }

    // [Step 3] 인덱스로 꺼내기
    String get(int index) {
        // TODO: index 위치의 값을 반환하세요.
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("범위 안 맞음");
        }
        else
            return arr[index];
    }

    // [Step 4] 현재 개수
    int size() {
        // TODO: size를 반환하세요.
        return size;
    }

    // [Step 5] 공간이 꽉 찼으면 배열을 2배로 늘리기
    private void ensureCapacity() {
        // TODO: size가 arr.length와 같으면
        //       arr = Arrays.copyOf(arr, arr.length * 2); 로 교체하세요.
        if(size == arr.length) arr= Arrays.copyOf(arr, arr.length*2);
    }

    // [Step 6] 맨 앞에 추가  ★핵심★
    void addFirst(String data) {
        // TODO: 1) ensureCapacity() 호출
        //       2) for 문으로 맨 뒤에서부터 arr[i] = arr[i - 1] 한 칸씩 밀기
        //       3) arr[0] = data;  그리고 size++
        ensureCapacity();
        for (int i = size; i > 0; i--) {
            arr[i] = arr[i-1];
        }
        arr[0] = data;
        size++;
    }

    // [도전] index 위치에 삽입
    void insert(int index, String data) {
        // TODO (도전): index 뒤의 요소들을 한 칸씩 밀고, 그 자리에 data를 넣으세요.
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("범위 안 맞음");
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            arr[i] = arr[i-1];
        }
        arr[index] = data;
        size++;
    }

    // [도전] index 위치 삭제
    void remove(int index) {
        // TODO (도전): index 뒤의 요소들을 한 칸씩 앞으로 당기고 size--
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("범위 안 맞음");
        }
        for (int i = index; i < size; i++) {
            arr[i] = arr[i+1];
        }
        arr[size-1] = null;
        size--;
    }
}