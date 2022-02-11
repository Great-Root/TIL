package com.company.report;

public class Main {

    public static void main(String[] args) {
        //이용자의 ID가 담긴 문자열 배열
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        //각 이용자가 신고한 이용자의 ID (이용자ID, 신고당한ID)
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        //정지 기준 신고횟수
        int k = 2;
        // 기대 결과 : 처리결과 메일 갯수를 return
        // 기대값 : 	[2, 1, 1, 0]
	    int[] result = new Solution().solution(id_list,report,k);
    }
}
