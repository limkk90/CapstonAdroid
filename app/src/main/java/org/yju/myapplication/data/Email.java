package org.yju.myapplication.data;

import lombok.Data;

@Data
public class Email {
    private String userEmail;
    private String confirm;

    @Override
    public String toString() {
        return "Email{" +
                "userEmail='" + userEmail + '\'' +
                ", confirm='" + confirm + '\'' +
                '}';
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
