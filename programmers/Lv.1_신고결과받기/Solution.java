package com.company.report;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        //신고 먹은 횟수를 담은 List인 HashMap을 준비
        HashMap<String, Integer> clickList = new HashMap<>();
        //정지 결과 메일 발송 횟수 List의 HashMap
        HashMap<String,Integer> resultList = new HashMap<>();
        //누가 신고했는지 리스트 <신고당한ID, 이용자ID>
        HashMap<String, HashSet<String>> whoClickList = new HashMap<>();
        for (String id: id_list) {
            //신고횟수 default값이 0인 리스트 생성
            clickList.put(id,0);
            //정지 결과 메일 발송 회수 List 생성
            resultList.put(id,0);
        }
        //System.out.println("초기 신고 리스트 : "+clickList);
        //System.out.println("정지 결과 리스트 : "+resultList);
        //신고 버튼 클릭 실행
        for (String click: report) {
            //report list에서 신고한 사람과 신고 당한 사람 분리 추출
            String[] click_result = click.split(" ");
            //신고 한사람
            String fromClick = click_result[0];
            //신고 당한 사람
            String toClick = click_result[1];

            //결과 출력
            //System.out.println("신고한 사람 : "+fromClick+"\t신고당한 사람 : "+toClick);

            //신고당한 List 최신화
            //신고 당한 ID 별 신고한 사람 List 생성 후 입력
            HashSet<String> fromClickList = whoClickList.getOrDefault(toClick, new HashSet<>());
            fromClickList.add(fromClick);
            whoClickList.put(toClick,fromClickList);

            //신고 당한 횟수
            int cnt = clickList.get(toClick)+1;
            //신고 당한 횟수 반영
            clickList.replace(toClick, cnt);

        }

        //결과메일 발송 횟수 반영
        for (String toClick: whoClickList.keySet() ) {
            if(whoClickList.get(toClick).size() >= k) {
                for (String fromClick : whoClickList.get(toClick)) {
                    resultList.replace(fromClick,resultList.get(fromClick)+1);
                }
            }
        }

//        System.out.println("신고 결과 리스트 : "+clickList);
//        System.out.println("결과 메일 리스트 : "+resultList);
//        System.out.println("신고 당한 리스트 : "+ whoClickList);
        int[] answer = new int[id_list.length];
        //결과를 int[]로 변환
        for (int i = 0; i < id_list.length ; i++ ) {
            answer[i] = resultList.get(id_list[i]);
        }
        return answer;
    }
}
