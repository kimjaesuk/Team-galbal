package com.ohgiraffers.phone.service;

import com.ohgiraffers.phone.dao.PhoneRepository;
import com.ohgiraffers.phone.dto.PhoneDTO;

import java.util.ArrayList;

public class PhoneService {

    private PhoneRepository phoneRepository;

    public PhoneService() {
        this.phoneRepository = new PhoneRepository();

    }
 /*
 * 연락처 상세조회: 서은진
 * */
    public PhoneDTO phoneFindbyName(String name) throws Exception {
        if (name == null && name.equals("")) {
            return null;
        }
        PhoneDTO emp = phoneRepository.phoneFindByName(name);
        if (emp == null) {
            throw new Exception("전화번호 조회 실패");
        }
        return emp;
    }


    public ArrayList phoneViewAll() throws Exception {
        ArrayList phones = phoneRepository.phoneViewAll();

        if (phones == null) {
            throw new Exception("전화번호 조회 실패");

        }
        return phones;
    }

    /*
     * 연락처 등록하기 : 김민주
     * */
    public String phoneInsert(PhoneDTO emp) throws Exception {

        PhoneDTO findEmp = phoneRepository.phoneFindByName(emp.getUserName());
        if (findEmp != null) {
            throw new Exception("이미 저장된 이름이 있습니다!!!!!");
        }

        int result = phoneRepository.phoneInsert(emp);

        if (result < 0) {
            throw new Exception("등록실패");
        }
        return (result > 0) ? "등록성공" : "등록실패";

    }

    public PhoneDTO phoneDelete(String name) throws Exception {
        PhoneDTO findPH = phoneRepository.phoneFindByName(name);
        if (findPH == null) {

            throw new Exception("삭제할 전화번호가 존재하지 않습니다.");
        }

        int result = phoneRepository.phoneDelete(name);
        if (result < 0) {
            throw new Exception("전화번호 삭제 실패");
        }

        PhoneDTO deletePh = phoneRepository.phoneFindByName(name);
        return deletePh;
    }

    public PhoneDTO phoneModify(PhoneDTO phone) throws Exception {
        PhoneDTO modifyPhone = phoneRepository.phoneFindByName(phone.getUserName());

        int result = phoneRepository.phoneModify(phone);
        if (result < 0) {
            throw new Exception("변경실패");
        }
        System.out.println("등록 성공");

        return modifyPhone;

    }
}
