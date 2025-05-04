package com.example.retrofit.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.model.User;
import com.example.retrofit.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    private UserRepository repository;

    public UserViewModel() {
        repository = new UserRepository();
        users= new MutableLiveData<>();
        loadUsers();
    }
    public LiveData<List<User>> getUsers() {
        return users;
    }
    public void loadUsers() {
        repository.fetchUsers(users);
    }
}
