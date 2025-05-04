package com.example.retrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.R;
import com.example.retrofit.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private List<User> userListFull;
    private TextView emptyView;

    public UserAdapter() {
        this.userList = new ArrayList<>();
        this.userListFull = new ArrayList<>();
    }

    public UserAdapter(TextView emptyView) {
        this.emptyView = emptyView;
        this.userList = new ArrayList<>();
        this.userListFull = new ArrayList<>();
    }

    public void setUserList(List<User> users) {
        this.userList = users;
        this.userListFull = new ArrayList<>(users);
        checkIfEmpty();
        notifyDataSetChanged();
    }


    public void filter(String text) {
        List<User> filteredList = new ArrayList<>();

        if (text.isEmpty()) {
            filteredList.addAll(userListFull);
        } else {
            String filterPattern = text.toLowerCase().trim();
            for (User user : userListFull) {
                if (user.getName().toLowerCase().contains(filterPattern)) {
                    filteredList.add(user);
                }
            }
        }

        this.userList = filteredList;
        checkIfEmpty();
        notifyDataSetChanged();
    }
    private void checkIfEmpty() {
        if (emptyView != null) {
            emptyView.setVisibility(userList.isEmpty() ? View.VISIBLE : View.GONE);
            if (userList.isEmpty()) {
                emptyView.setText("Aucun utilisateur trouvé");
            }
        }
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phone, city;

        public UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userName);
            email = itemView.findViewById(R.id.userEmail);
            phone = itemView.findViewById(R.id.userPhone);
            city = itemView.findViewById(R.id.userCity);
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());
        holder.city.setText(user.getCity());
    }

    @Override
    public int getItemCount() {
        return (userList == null) ? 0 : userList.size();
    }
}

