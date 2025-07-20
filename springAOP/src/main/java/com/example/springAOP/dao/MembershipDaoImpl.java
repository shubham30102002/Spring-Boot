package com.example.springAOP.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDaoImpl implements MembershipDao{
    @Override
    public void addSillyMember() {
        System.out.println(getClass().getSimpleName() + ": Doing my db work, adding silly member");
    }

    @Override
    public void updateSillyMember() {
        System.out.println(getClass().getSimpleName() + ": Doing my db work, updating silly member");

    }
}
