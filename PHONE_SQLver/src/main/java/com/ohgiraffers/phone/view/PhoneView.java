package com.ohgiraffers.phone.view;

import com.ohgiraffers.phone.dto.PhoneDTO;
import com.ohgiraffers.phone.service.PhoneService;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneView {
    private static boolean state = true;
    private static PhoneService phoneService = new PhoneService();

    public static void run() {

        while (state) {
            System.out.println("실행할 번호를 입력해주세요: ");
            System.out.println("1. 연락처 등록하기");
            System.out.println("2. 연락처 전체조회하기");
            System.out.println("3. 연락처 상세조회하기");
            System.out.println("4. 연락처 수정하기");
            System.out.println("5. 연락처 삭제하기");
            Scanner sc = new Scanner(System.in);
            int index = Integer.parseInt(sc.nextLine());

            switch (index) {
                case 1:
                    PhoneInsert();
                    break;
                case 2:
                    PhoneViewAll();
                    break;
                case 3:
                    PhoneFindByName();
                    break;
                case 4:
                    phoneUpdate();
                    break;
                case 5:
                    phoneDelete();
                    break;

            }

            System.out.println("종료하시겠습니까? (yes를 입력하면 종료)");
            String result = sc.nextLine();
            if (result.equals("yes")) {
                state = false;
                sc.close();
            }
        }

    }


    private static void PhoneInsert() {
        Scanner sc = new Scanner(System.in);
//        String result = null;
        PhoneDTO emp = new PhoneDTO();

        System.out.println("등록할 사람의 정보를 입력해주세요 ");
        System.out.print("이름를 입력해주세요 : ");
        emp.setUserName(sc.nextLine());
        System.out.print("주소을 입력해주세요 : ");
        emp.setUserAddress(sc.nextLine());
        System.out.print("전화번호를 입력해주세요 : ");
        emp.setCallNumber(sc.nextLine());
        System.out.print("전화번호 종류를 입력하세요 :");
        emp.setCallName(sc.nextLine());
        System.out.print("대표번호로 설정할거면 Y, 아님 N 을 입력하세요");
        emp.setMainCall(sc.nextLine());


        try {
            String result = phoneService.phoneInsert(emp);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    /*
     * 연락처 전체조회 : 김재석
     * */

    public static void PhoneViewAll() {
        System.out.println("전화번호부 전체 조회");
//        ArrayList emp = new ArrayList();
        try {
            ArrayList emp = phoneService.phoneViewAll();
            System.out.print(emp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    public static void PhoneFindByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 연락처의 이름을 입력하세요 : ");
        String name = sc.nextLine();
        PhoneDTO emp = null; // emp 가 뭐지?

        try {
            emp = phoneService.phoneFindbyName(name);
            System.out.print(emp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /*
     * 연락처 삭제하기 : 김다영
     * */
    public static void phoneDelete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 연락처의 이름을 입력하세요 : ");      // 이름 입력
        String name = sc.nextLine();
        PhoneDTO ph = null; // 강제 초기화

        try {
            ph = phoneService.phoneFindbyName(name);        // 서비스에 있는 메소드(매개변수 : name)
//            System.out.println(ph);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (ph == null) {
            System.out.println("삭제할 전화번호가 존재하지 않습니다.22");
        } else {
            System.out.print("삭제하시겠습니까? ( yes / no ) : ");
            String check = sc.nextLine();
            if (check.equalsIgnoreCase("yes")) {
                try {
                    phoneService.phoneDelete(name);
                    System.out.println("삭제가 완료되었습니다.");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("다시 시도해주세요.");
            }
        }
    }

    public static void phoneUpdate() {
        /*
         * 연락처 수정하기 : 박하영
         * */
        System.out.println("변경할 정보의 이름을 입력하세요");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        PhoneDTO phone = null;
        try {
            phone = phoneService.phoneFindbyName(userName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (phone == null) {
            System.out.println("변경할 정보가 존재하지 않습니다.");
            return;
        }
        System.out.println(phone);
        System.out.println("어떤 정보를 수정하시겠습니까? 1.이름/2.전화번호/3.주소 : (숫자만)");
        int value = sc.nextInt();
        sc.nextLine();

        switch (value) {
            case 1:
                System.out.println("변경할 이름을 입력해주세요");
                phone.setUserName(sc.nextLine());
                break;
            case 2:
                System.out.println("변경할 전화번호를 입력해주세요");
                phone.setCallNumber(sc.nextLine());
                break;
            case 3:
                System.out.println("변경할 주소를 입력해주세요");
                phone.setUserAddress(sc.nextLine());
                break;
            default:

                System.out.println("입력 오류");
                break;
        }

        try {
            PhoneDTO modifyPhone = phoneService.phoneModify(phone);
//            System.out.println(modifyPhone);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}



