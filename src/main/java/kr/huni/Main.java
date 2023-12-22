package kr.huni;

import java.io.IOException;
import java.util.Scanner;
import kr.huni.code_generator.CodeGenerator;
import kr.huni.problem_parser.ProblemParser;

public class Main {

  public static void main(String[] args) throws IllegalArgumentException, IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("백준 문제 번호를 입력해주세요: ");
    int number = scanner.nextInt();
    ProblemParser parser = new ProblemParser(number);

    CodeGenerator generator = new CodeGenerator(parser.getProblem());
    generator.generate();
    generator.runIdea();

    scanner.close();
  }

}