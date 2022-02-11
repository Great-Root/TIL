package com.company.id;

class Solution {
    public String removeDot(String removeString){
        //첫번째 마침표(.) 제거
        char[] tempCharArray= removeString.toCharArray();
        if(tempCharArray[0] == '.'){
            removeString = removeString.replaceFirst(".", "");
        }else if(tempCharArray[tempCharArray.length-1] == '.') {
            //문자열 돌려서 마침표(.) 제거
            removeString = new StringBuffer(new StringBuffer(removeString).reverse().toString().replaceFirst(".", "")).reverse().toString();
        }

        //가공 후 answer에 반영
        return removeString;
    }
    public String solution(String new_id) {
        String answer = new_id;
        // 1단계 : 모든 대문자 -> 소문자 치환
        answer = answer.toLowerCase();

        // 2단계 : 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거
        // 금지할 특수문자 관리하기 편하게 배열로 변환
        String[] doNotChar = "~!@#$%^&*()=+[{]}:?,<>/".split("");
        // 금지문자 삭제
        for (int i = 0; i < doNotChar.length; i++) {
            answer = answer.replace(doNotChar[i],"");
        }

        //3단계 : 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환
        char[] answer_ch= answer.toCharArray();
        for (int i = 0; i < answer_ch.length - 1; i++) {
            if(answer_ch[i] == '.' && answer_ch[i+1] == '.') {
                answer_ch[i] = ' ';
            }
        }
        //공백 제거
        answer = String.copyValueOf(answer_ch).replace(" ", "");

        //4단계 : 마침표(.)가 처음이나 끝에 위치한다면 제거
        answer = removeDot(answer);
        //5단계 : 빈 문자열이라면, new_id에 "a"를 대입
        if(answer.length() == 0){
            answer += "a";
        }

        //6단계 : new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
        //     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        //16글자 이상인 경우 첫 15글자로 자른다.
        answer = answer.length() >= 16 ? answer.substring(0,15) : answer;
        //앞뒤 마침표(.)를 제거해준다.
        answer = removeDot(answer);

        //7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        while (answer.length() <= 2){
            char[] temp = answer.toCharArray();
            answer += temp[temp.length - 1];
        }
        return answer;
    }
}
