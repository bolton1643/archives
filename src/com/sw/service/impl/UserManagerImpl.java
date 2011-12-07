package com.sw.service.impl;

import java.util.List;

import com.sw.dao.UserDao;
import com.sw.pojo.TreeNodeRight;
import com.sw.pojo.User;
import com.sw.service.UserManager;

public class UserManagerImpl implements UserManager {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public int validLogin(String name, String pass) {
        if (userDao.findByNameAndPass(name, pass) != null) {
            return LOGIN_SUCCESS;
        } else {
            return LOGIN_FAIL;
        }
    }

    public void registe(User user) {
        userDao.save(user);
    }

    public User getUserByNameAndPass(String name, String pass) {
        return userDao.findByNameAndPass(name, pass);
    }

    public void changePwd(Integer userId, String newPwd) {
        User user = userDao.get(userId);
        user.setPassword(newPwd);
        userDao.update(user);
    }

    @Override
    public List<User> userList() {
        return userDao.findAll();
    }
    
    @Override
    public User getUserById(User user){
        return userDao.get(user.getUserId());
    }
    
    @Override
    public void modifyUser(User user){
         userDao.save(user);
    }
    
    public List<TreeNodeRight> findUserRight(User user){
        return userDao.findUserRight(user.getUserId());
    }
    
    public void saveRight(List <TreeNodeRight> tl,int userId){
        userDao.saveRight(tl,userId);
    }
}
