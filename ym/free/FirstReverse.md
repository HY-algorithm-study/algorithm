FirstFactorial
===========================================

<!-- TOC -->

- [문제 정의](#문제-정의)
- [input & output 정의](#input--output-정의)
- [알고리즘](#알고리즘)

<!-- /TOC -->

## 문제 정의
문자열을 반대로 뒤집은 문자열을 구하는 함수 작성

## input & output 정의
- input : 문자열 str
- output : str의 역순인 문자열

ex) "coderbyte" --> "etybredoc"

## 알고리즘

1. 반복을 이용한 방법
- 문자열의 마지막 character 부터 역순 탐색
    - 마지막 character부터 하나의 문자열로 통합

2. 자료구조를 이용한 방법
- Stack은 후입선출(Last-In First-Out)의 자료구조이기 때문에 Stack을 이용
    - Stack에 str의 character를 하나씩 모두 집어 넣는다.(push)
    - Stack의 요소를 모두 뽑아 내어 하나의 문자열으로 만든다.(pop)